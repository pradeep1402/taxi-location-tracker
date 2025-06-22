package Delivery

import scala.util.Random

case class DeliveryAgent(id: String, var currentLocation: Int) {
  def moving(): Unit = {
    this.currentLocation += Random.nextInt(5) - 2;
  }
}