package consumer

import org.apache.spark.sql.{DataFrame, SparkSession}

case class Consumer(spark: SparkSession, currentLocation: Int = 0) {
  def exe(userKey: String): Unit = {
    val kafkaDf: DataFrame = config()

    import spark.implicits._

    val filteredMessages = kafkaDf
      .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
      .as[(String, String)]
      .filter(_._1 == userKey)
      .toDF("key", "value")

    val query = filteredMessages.writeStream
      .outputMode("append")
      .format("console")
      .option("numRows", 1000)
      .start()

    query.awaitTermination()
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
