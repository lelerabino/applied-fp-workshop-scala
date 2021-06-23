package firststeps

/*
 * In OOP model object that encapsulate data and expose behaviours.
 * This two concepts are brigs together thanks to class definitions.
 *
 * In FP data and behaviours are modelled with two different tools:
 * - Algebraic Data Type (ADT) to model data
 * - Function to model behaviours
 *
 * An ADT is an immutable data structure that compose other types.
 * There are two common kinds of composition strategy:
 * - Product type: put many types together. e.g. struct in C, POJO in JAVA.
 *                 It's called product because all the possible values of a Tuple[String, Int] is
 *                 the *product* of all possible String with all possible Int.
 *                 Useful to model independent data in AND e.g. a Person is composed by a name *and* an age.
 *
 * - Sum type:     model exclusive types e.g. union in C, enum in JAVA.
 *                 Sum types correspond to disjoint unions of sets.
 *                 It's called sum because all the possible values of a Either[String, Int] is
 *                 the *sum* of all possible String with all possible Int.
 *                 Useful to model dependant data in OR e.g. the Light is on *or* off.
 *
 * We can mix ADT as we want, like a product type that compose a type with a sum type.
 */

class ModelData extends munit.FunSuite {
  // Proper type
  // es: Int, String

  // Type alias
  type Name = String

  // Type wrapper
  case class Age(value: Int)

  // Product type
  case class Person(name: String, age: Int)

  // Sum type
  sealed trait LightState
  case class On()  extends LightState
  case class Off() extends LightState

  /*
   * TODO: Model "Scopa" the italian card game, below the game description. :-)
   *       After modeling the domain implements the test following the description of ignores.
   *
   * DESCRIPTION:
   *       It is played (let simplify) between two players with
   *       a standard Italian 40-card deck, divided into four suits: Cups, Golds, Swords, Clubs.
   *       The values on the cards range numerically from one through seven,
   *       plus three face cards in each suit: Knight (worth 8), Queen (worth 9) and King (worth 10).
   *       Each player receives three cards. The dealer will also place four cards face up on the table.
   */



  sealed trait Suit
  case object Cups extends Suit
  case object Golds extends Suit
  case object Swords extends Suit
  case object Clubs extends Suit

  sealed trait Value
  case object Ace extends Value
  case object Two extends Value
  case object Three extends Value
  case object Four extends Value
  case object Five extends Value
  case object Six extends Value
  case object Seven extends Value
  case object Knight extends Value
  case object Queen extends Value
  case object King extends Value

  case class Card(suit:Suit, value: Value)

  case class Player(name:String, hand: List[Card], pile: List[Card])

  case class Table(cards:List[Card])
  case class Deck(cards:List[Card])
  case class Game(deck: Deck, table: Table, player1: Player, player2: Player)



  test("represent initial match state".ignore) {
    // TODO: build the first player w/ 2 of Golds, 5 of Swords and 7 of Clubs
    val player1 = Player("p1", List(Card(Golds, Two), Card(Swords, Five), Card(Clubs, Seven)), List())
    // TODO: build the second player w/ 1 of Cups, 2 of Clubs and 9 of Golds
    val player2 = Player("p2", List(Card(Cups, Ace), Card(Clubs, Two), Card(Golds, Queen)), List())
    // TODO: build the table w/ 4 of Clubs, 10 of Swords, 8 of Golds and 1 of Swords
    val table = Table(List(Card(Clubs, Four),Card(Swords, King),Card(Golds, Knight),Card(Swords, Ace)))
    // TODO: build the deck w/ only 1 of Swords and 10 of Clubs
    val deck = Deck(List(Card(Swords, Ace), Card(Clubs, King)))
    // TODO: build the game
    val game = Game(deck, table, player1, player2)
  }
}
