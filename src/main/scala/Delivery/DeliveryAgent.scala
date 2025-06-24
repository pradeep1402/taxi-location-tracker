package Delivery

import scala.util.Random

case class DeliveryAgent(id: String, var currentLocation: Int, randomDestination: () => Int = () => Random.nextInt(6)) {
  def moving(): Unit = {
    this.currentLocation += randomDestination() - 3;
  }
}
