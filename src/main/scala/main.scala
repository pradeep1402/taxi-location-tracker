@main
def main(): Unit = {
  val agents: List[DeliveryAgent] = List(DeliveryAgent("1", 12), DeliveryAgent("2", 15), DeliveryAgent("3", 20))
  println("Starting Kafka Producer for delivery agents...")
  agents.foreach(agent => {
    KafkaProducer.run(agent)
  })
  while (true) Thread.sleep(1000)
}