package consumer

import org.apache.spark.sql.functions.last
import org.apache.spark.sql.{DataFrame, SparkSession, functions}

import java.sql.Timestamp
import scala.util.Try

case class Consumer(spark: SparkSession, currentLocation: Int = 0) {
  def nearestAgent(): Unit = {
    val kafkaDf: DataFrame = config()

    import spark.implicits._

    val parsedAgentData = kafkaDf
      .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)", "timestamp")
      .as[(String, String, Timestamp)]
      .filter(row => Try(row._2.toInt).isSuccess)
      .map(row => (row._1, row._2.toInt, row._3, Math.abs(row._2.toInt - currentLocation)))
      .toDF("key", "value", "timestamp", "distance")

    val filteredMessages = parsedAgentData
      .withWatermark("timestamp", "1 minute")
      .groupBy($"key")
      .agg(last($"value").as("last_location"),
        last($"distance").as("distance"))
      .sort($"distance".asc)
      .limit(2)

    filteredMessages.writeStream
      .outputMode("complete")
      .format("console")
      .option("numRows", 10)
      .start()
      .awaitTermination()
  }

  private def config() = {
    spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "trip-tracker")
      .option("startingOffsets", "earliest")
      .load()

  }
}
