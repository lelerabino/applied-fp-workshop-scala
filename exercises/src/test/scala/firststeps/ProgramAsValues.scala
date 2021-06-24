package firststeps

/*
 * Combine program is different from combine values.
 *
 * In order to compose program we need to work at a meta level.
 * We must split a program in two parts:
 * - description: build a program description
 * - evaluation: execute logic based on the description
 *
 * Is the good old idea of separation of concerns applied
 * to the whole program.
 *
 * The final result is that when a we invokes functions
 * they aren't executed but instead build data structures.
 * At this point we can combine those data structures as we want
 * and combine different programs in one.
 * In the end the data structure tree will be evaluated and
 * produce a final result.
 *
 * In this context we gain an inversion of control on execution
 * that enable a better program composition.
 */

class ProgramAsValues extends munit.FunSuite {

  /*
   * TODO: implements functions marked with `???`
   */

  object Immediate {

    def plus(x: Int, y: Int): Int =
      x + y

    def times(x: Int, y: Int): Int =
      x * y
  }

  test("immediate execution") {
    import Immediate._
    val result = times(plus(1, 1), 2)
    assertEquals(result, 4)
  }

  object SplitBuildFromExecute {
    type Num = () => Int

    def num(x: Int): Num =
      () => x

    def plus(x: Num, y: Num): Num =
      () => x() + y()

    def times(x: Num, y: Num): Num =
      () => x() * y()

  }

  test("split building a program from executing it") {
    // TODO: implements SplitBuildFromExecute functions
    import SplitBuildFromExecute._
    val program = times(plus(num(1), num(1)), num(2))
    val result  = program()
    assertEquals(result, 4)
  }

  object DifferentEvaluator {
    sealed trait Expr
    case class Num(n: Int)                       extends Expr
    case class Sum(left: Expr, right: Expr)      extends Expr
    case class Multiply(left: Expr, right: Expr) extends Expr

    def num(x: Int): Expr =
      Num(x)

    def plus(x: Expr, y: Expr): Expr =
      Sum(x, y)

    def times(x: Expr, y: Expr): Expr =
      Multiply(x, y)

    def eval(e: Expr): Int =
      e match {
        case Num(n)                => n
        case Sum(left, right)      => eval(left) + eval(right)
        case Multiply(left, right) => eval(left) * eval(right)
      }

    def evalPrint(e: Expr): String =
      e match {
        case Num(n)                => n.toString
        case Sum(left, right)      => s"(${evalPrint(left)} + ${evalPrint(right)})"
        case Multiply(left, right) => s"(${evalPrint(left)} * ${evalPrint(right)})"
      }
  }

  test("execute program w/ different evaluator") {
    // TODO: implements DifferentEvaluator functions
    import DifferentEvaluator._
    val program = times(plus(num(1), num(1)), num(2))
    assertEquals(eval(program), 4)
    assertEquals(evalPrint(program), "((1 + 1) * 2)")
  }
}
