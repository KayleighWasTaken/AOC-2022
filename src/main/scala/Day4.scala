import scala.util.Using
import scala.io.Source

@main def main = {
  val input = Using.resource(Source.fromFile("input-day4.txt"))(_.getLines.toList)
  val assignments = input.map(s => {
    val xs = s.split(',').map(_.split('-').map(_.toInt))
    (xs.head, xs.last)
  })
  val partOne = assignments.count {case (a, b) => a.forall((b.head to b.last) contains _) || b.forall((a.head to a.last) contains _)}
  println(s"Part 1: $partOne")
  val partTwo = assignments.count {case (a, b) => a.exists((b.head to b.last) contains _) || b.exists((a.head to a.last) contains _)}
  println(s"Part 2: $partTwo")
}