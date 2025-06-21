@main
def main(): Unit = {
  val agents: List[DeliveryAgent] = List(DeliveryAgent("1", 12), DeliveryAgent("2", 15), DeliveryAgent("3", 20))

  agents.foreach(agent =>
    println(s"Agent ID: ${agent.id}, Current Location: ${agent.currentLocation}")
  )
}