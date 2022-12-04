import scala.util.Using
import scala.io.Source

@main def main = {
  val input = Using.resource(Source.fromFile("input-day3.txt"))(_.getLines.toList)
  val priorities = (('a' to 'z') ++ ('A' to 'Z')).zip(1 to 52).toMap
  val partOne = input.map(s => {
    val secondHalf = s.takeRight(s.length / 2)
    s.collectFirst{case x if secondHalf contains x => priorities(x)}.get
  }).sum
  println(s"Part 1: $partOne")
  val partTwo = input.grouped(3).map {
    case Seq(a, b, c) => priorities((a intersect b intersect c).head)
    case _ => 0 //makes compiler shut about non-exhaustive match
    //alternatively: x => priorities(x.tail.fold(x.head)(_ intersect _).head)
  }.sum
  println(s"Part 2: $partTwo")
}
