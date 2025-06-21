import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import java.util.Properties

object KafkaProducer {
  def main(args: Array[String]): Unit = {
    val prop = new Properties()
    prop.put("bootstrap.servers", "localhost:9092")
    prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](prop)
    val topic = "trip-tracker"
    val record = new ProducerRecord[String, String](topic, "key", "Hello Kafka from Scala")
    producer.send(record)
    producer.close()
  }
}
