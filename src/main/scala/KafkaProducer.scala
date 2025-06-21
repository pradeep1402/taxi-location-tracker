import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import java.util.Properties

object KafkaProducer {
  def main(args: Array[String]): Unit = {
    val prop: Properties = config()
    val producer = new KafkaProducer[String, String](prop)
    val topic = "trip-tracker"

    val record = new ProducerRecord[String, String](topic, "userId", "Hello Kafka from Scala")

    producer.send(record)
    producer.close()
  }

  private def config (): Properties = {
    val prop = new Properties()
    prop.put("bootstrap.servers", "localhost:9092")
    prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    prop
  }
}
