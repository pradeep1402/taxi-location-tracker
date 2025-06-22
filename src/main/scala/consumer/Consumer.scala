package consumer

import org.apache.spark.sql.{DataFrame, SparkSession}

case class Consumer(spark: SparkSession, currentLocation: Int = 0) {
  def run(): Unit = {
    println(s"Consumer is at location $currentLocation")
  }

  def exe(userKey: String): Unit = {
    spark.sparkContext.setLogLevel("ERROR")

    val kafkaDf = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "trip-tracker")
      .option("startingOffsets", "earliest")
      .load()

    import spark.implicits._

    val filteredMessages = kafkaDf
      .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
      .as[(String, String)]
      .filter(_._1 == userKey)
      .toDF("key", "value")

    println(s"kafka df created... $userKey")

    val query = filteredMessages.writeStream
      .outputMode("append")
      .format("console")
      .option("numRows", 1000)
      .start()

    query.awaitTermination()
  }
}
