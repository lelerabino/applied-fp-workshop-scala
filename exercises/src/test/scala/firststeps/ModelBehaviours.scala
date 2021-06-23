package firststeps

/*
 * ADT models data while Function models behaviour.
 * A function is simply something that accepts an input value
 * and produces an output value.
 * In more academic terms it connects a Domain to a Codomain.
 * Functions are described/documented by it's type definition.
 *
 *  f:  InType => OutType
 */

class ModelBehaviours extends munit.FunSuite {

  /*
   * TODO: implements functions marked with `???`
   */

  val asString: Double => String = in => in.toString

  val parseString: String => Int = in => in.toInt

  val reciprocal: Int => Double = in => 1.0 / in

  val reciprocalString: String => String = in => {
    asString(reciprocal(parseString(in)))
  }

  val numericReciprocalOfString: String => Double = in =>  reciprocal(parseString(in))

  val reciprocalString1: String => String = in => {
    (asString compose numericReciprocalOfString)(in)
  }




  test("from string to string throught reciprocal") {
    assertEquals(reciprocalString("42"), "0.023809523809523808")
  }

  test("from string to string throught reciprocal1") {
    assertEquals(reciprocalString1("42"), "0.023809523809523808")
  }
}
