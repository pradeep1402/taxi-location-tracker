package consumer

import org.apache.spark.sql.SparkSession

object main {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("trip-tracker-consumer")
      .master("local[4]")
      .getOrCreate()

    val consumer1 = Consumer(spark, 10)
    consumer1.nearestAgent()
  }
}
