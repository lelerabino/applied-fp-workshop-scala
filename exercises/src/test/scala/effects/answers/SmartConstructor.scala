package exercises.answers

import minitest._

object SmartConstructorTests extends SimpleTestSuite {

  sealed trait MaybeItem
  case class ValidItem(value: Item) extends MaybeItem
  case class InvalidItem()          extends MaybeItem

  case class Item(qty: Int)

  def createItem(qty: String): MaybeItem =
    if (qty.matches("^[0-9]+$")) ValidItem(Item(qty.toInt))
    else InvalidItem()

  test("valid") {
    assertEquals(createItem("100"), ValidItem(Item(100)))
  }

  test("invalid") {
    assertEquals(createItem("asd"), InvalidItem())
    assertEquals(createItem("1 0 0"), InvalidItem())
    assertEquals(createItem(""), InvalidItem())
  }

}