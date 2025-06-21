import scala.util.Random

case class DeliveryAgent(id: String, currentLocation: Int):
  def moving(): DeliveryAgent = {
    DeliveryAgent(this.id, this.currentLocation + 1)
  }