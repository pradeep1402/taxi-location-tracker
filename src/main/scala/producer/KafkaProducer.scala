package producer

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.internals.Topic

import java.util.{Properties, Timer, TimerTask}
import Delivery.DeliveryAgent

object KafkaProducer {
  def run(agent: DeliveryAgent): Unit = {
    val timer = new Timer()
    val task  = new TimerTask {
      override def run(): Unit = sendLocation(agent)
    }

    timer.schedule(task, 0, 5000)
  }

  private def sendLocation(agent: DeliveryAgent): Unit = {
    val topic: String                           = "trip-tracker"
    val prop: Properties                        = config()
    val producer: KafkaProducer[String, String] = new KafkaProducer[String, String](prop)
    val key: String                             = agent.id
    val location: String                        = agent.currentLocation.toString
    val record: ProducerRecord[String, String]  = new ProducerRecord[String, String](topic, key, location)
    agent.moving()

    producer.send(record)
    println(s"Sent location for agent ${agent.id}: $location")
  }

  private def config(): Properties = {
    val prop = new Properties()
    prop.put("bootstrap.servers", "localhost:9092")
    prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    prop
  }
}
