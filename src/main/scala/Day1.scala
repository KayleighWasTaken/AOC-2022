import scala.util.Using
import scala.io.Source

@main def main = {
  val input = Using.resource(Source.fromFile("input-day1.txt"))(_.mkString)
  val data = input.split("\r\n\r\n").map(_.split("\r\n").map(_.toInt).sum).sorted
  println(s"Part 1: ${data.last}")
  println(s"Part 2: ${data.takeRight(3).sum}")
}
