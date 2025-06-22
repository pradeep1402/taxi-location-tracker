package Delivery
import org.scalatest.funsuite.AnyFunSuiteLike

class DeliveryAgentTest extends  AnyFunSuiteLike {
  test("DeliveryAgent moving method should update currentLocation correctly") {
    val agent = DeliveryAgent("1", 10, () => 5)
    agent.moving()
    assert(agent.currentLocation == 12)
  }

  test("DeliveryAgent moving method should handle negative currentLocation") {
    val agent = DeliveryAgent("2", 0, () => 0)
    agent.moving()
    assert(agent.currentLocation == -3)
  }
}
