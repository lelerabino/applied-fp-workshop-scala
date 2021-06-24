package marsroverkata

object Version1 {

  case class Point(x: Int, y: Int)

  case class Rover(position: Point, orientation: Direction)

  case class Planet(width: Int, height: Int)

  sealed trait Direction
  case object North extends Direction
  case object South extends Direction
  case object West  extends Direction
  case object Est   extends Direction

  sealed trait Command
  case object Forward  extends Command
  case object Backward  extends Command
  case object TurnRight extends Command
  case object TurnLeft extends Command

  def eval(command: Command, rover: Rover, planet: Planet): Rover =
    command match {
      case Forward  => moveForward(rover, planet)
      case Backward  => moveBackward(rover, planet)
      case TurnLeft => rover.copy(orientation = turnLeft(rover.orientation))
      case TurnRight => rover.copy(orientation = turnRight(rover.orientation))
    }

  private def turnRight(direction: Direction): Direction =
    direction match {
      case North => Est
      case Est   => South
      case South => West
      case West  => North
    }

  private def turnLeft(direction: Direction): Direction =
    direction match {
      case North => West
      case West  => South
      case South => Est
      case Est   => North
    }

  private def moveForward(rover: Rover, planet: Planet): Rover = {
    val newPosition = rover.orientation match {
      case North => moveToNorth(rover, planet)
      case South => moveToSouth(rover, planet)
      case West => moveToWest(rover, planet)
      case Est => moveToEst(rover, planet)
    }
    rover.copy(position = newPosition)
  }

  private def moveBackward(rover: Rover, planet: Planet): Rover = {
    val newPosition = rover.orientation match {
      case North => moveToSouth(rover, planet)
      case South => moveToNorth(rover, planet)
      case West => moveToEst(rover, planet)
      case Est => moveToWest(rover, planet)
    }
    rover.copy(position = newPosition)
  }

  private def moveToSouth(rover: Rover, planet: Planet) = {
    rover.position.copy(y = (planet.height + (rover.position.y - 1)) % planet.height)
  }

  private def moveToNorth(rover: Rover, planet: Planet) = {
    rover.position.copy(y = (rover.position.y + 1) % planet.height)
  }

  private def moveToWest(rover: Rover, planet: Planet) = {
    rover.position.copy(x = (planet.width + rover.position.x - 1) % planet.width)
  }

  private def moveToEst(rover: Rover, planet: Planet) = {
    rover.position.copy(x = (rover.position.x + 1) % planet.width)
  }
}
