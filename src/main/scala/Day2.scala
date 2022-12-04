import scala.util.Using
import scala.io.Source

@main def main = {
  val input = Using.resource(Source.fromFile("input-day2.txt"))(_.getLines.toList)
  val partOne = input.map {
    case "A X" => 4
    case "A Y" => 8
    case "A Z" => 3
    case "B X" => 1
    case "B Y" => 5
    case "B Z" => 9
    case "C X" => 7
    case "C Y" => 2
    case "C Z" => 6
  }.sum
  println(s"Part 1: $partOne")
  val partTwo = input.map {
    case "A X" => 3
    case "A Y" => 4
    case "A Z" => 8
    case "B X" => 1
    case "B Y" => 5
    case "B Z" => 9
    case "C X" => 2
    case "C Y" => 6
    case "C Z" => 7
  }.sum
  println(s"Part 2: $partTwo")
}
