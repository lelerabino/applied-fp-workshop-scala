package marsroverkata

import marsroverkata.Version1._

class Version1Tests extends munit.FunSuite {

// +-----+-----+-----+-----+-----+
// | 0,3 |     |     |     | 4,3 |
// +-----+-----+-----+-----+-----+
// |     |     |     |     |     |
// +-----+-----+-----+-----+-----+
// |     |     |     |     |     |
// +-----+-----+-----+-----+-----+
// | 0,0 |     |     |     | 4,0 |
// +-----+-----+-----+-----+-----+

  test("turn right command") {
    val planet = Planet(5, 4)
    val rover  = Rover(Point(0, 0), North)
    val cmd    = TurnRight

    assertEquals(eval(cmd, rover, planet), Rover(Point(0, 0), Est))
  }

  test("turn left command") {
    val planet = Planet(5, 4)
    val rover  = Rover(Point(0, 0), North)
    val cmd    = TurnLeft

    assertEquals(eval(cmd, rover, planet), Rover(Point(0, 0), West))
  }

  test("move forward command") {
    val planet = Planet(5, 4)
    val rover  = Rover(Point(0, 1), North)
    val cmd    = Forward

    assertEquals(eval(cmd, rover, planet), Rover(Point(0, 2), North))
  }

  test("move forward command, opposite orientation") {
    val planet = Planet(5, 4)
    val rover  = Rover(Point(0, 1), South)
    val cmd    = Forward

    assertEquals(eval(cmd, rover, planet), Rover(Point(0, 0), South))
  }

  test("move backward command") {
    val planet = Planet(5, 4)
    val rover  = Rover(Point(0, 1), North)
    val cmd    = Backward

    assertEquals(eval(cmd, rover, planet), Rover(Point(0, 0), North))
  }

  test("move backward command, opposite orientation") {
    val planet = Planet(5, 4)
    val rover  = Rover(Point(0, 1), South)
    val cmd    = Backward

    assertEquals(eval(cmd, rover, planet), Rover(Point(0, 2), South))
  }

  test("wrap on North") {
    val planet = Planet(5, 4)
    val rover  = Rover(Point(0, 3), North)
    val cmd    = Forward

    assertEquals(eval(cmd, rover, planet), Rover(Point(0, 0), North))
  }

  test("wrap on South") {
    val planet = Planet(5, 4)
    val rover  = Rover(Point(0, 0), South)
    val cmd    = Forward

    assertEquals(eval(cmd, rover, planet), Rover(Point(0, 3), South))
  }

  test("wrap on Est") {
    val planet = Planet(5, 4)
    val rover  = Rover(Point(4, 1), Est)
    val cmd    = Forward

    assertEquals(eval(cmd, rover, planet), Rover(Point(0, 1), Est))
  }

  test("wrap on West") {
    val planet = Planet(5, 4)
    val rover  = Rover(Point(0, 1), West)
    val cmd    = Forward

    assertEquals(eval(cmd, rover, planet), Rover(Point(4, 1), West))
  }

  test("wrap on West backward") {
    val planet = Planet(5, 4)
    val rover  = Rover(Point(0, 1), Est)
    val cmd    = Backward

    assertEquals(eval(cmd, rover, planet), Rover(Point(4, 1), Est))
  }

  test("wrap on North backward") {
    val planet = Planet(5, 4)
    val rover  = Rover(Point(0, 3), South)
    val cmd    = Backward

    assertEquals(eval(cmd, rover, planet), Rover(Point(0, 0), South))
  }

  test("wrap on South backward") {
    val planet = Planet(5, 4)
    val rover  = Rover(Point(0, 0), North)
    val cmd    = Backward

    assertEquals(eval(cmd, rover, planet), Rover(Point(0, 3), North))
  }

  test("wrap on Est backward") {
    val planet = Planet(5, 4)
    val rover  = Rover(Point(4, 1), West)
    val cmd    = Backward

    assertEquals(eval(cmd, rover, planet), Rover(Point(0, 1), West))
  }
}
