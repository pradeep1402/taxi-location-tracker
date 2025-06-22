package consumer

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object main {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("trip-tracker-consumer")
      .master("local[4]")
      .getOrCreate()
println("spark session created...")
    val consumer1 = Consumer(spark, 0)
    consumer1.exe("1")
  }
}