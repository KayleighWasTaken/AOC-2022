import scala.util.Using
import scala.io.Source

@main def main = {
  val input = Using.resource(Source.fromFile("input-day6.txt"))(_.mkString)
  val partOne = input.sliding(4).indexWhere(_.distinct.size == 4) + 4
  println(s"Part 1: $partOne")
  val partTwo = input.sliding(14).indexWhere(_.distinct.size == 14) + 14
  println(s"Part 1: $partTwo")
}
