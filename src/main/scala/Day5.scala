import scala.annotation.nowarn
import scala.util.Using
import scala.io.Source

@main @nowarn def main = {
  val input = Using.resource(Source.fromFile("input-day5.txt"))(_.getLines.toList)
  val stacks = parseStacks(input.take(8))
  val moves = input.drop(10).map(_.split(' ').collect {case x if x.forall(_.isDigit) => x.toInt})
  val partOne = moves.foldLeft(stacks) {
    case (stacks, Array(amount, from, to)) =>
      val (movedCrates, newFromStack) = stacks(from - 1).splitAt(amount)
      val newToStack = movedCrates.reverse ++ stacks(to - 1)
      stacks.updated(from - 1, newFromStack).updated(to - 1, newToStack)
  }.map(_.head).mkString
  println(s"Part 1: $partOne")
  val partTwo = moves.foldLeft(stacks) {
    case (stacks, Array(amount, from, to)) =>
      val (movedCrates, newFromStack) = stacks(from - 1).splitAt(amount)
      val newToStack = movedCrates ++ stacks(to - 1)
      stacks.updated(from - 1, newFromStack).updated(to - 1, newToStack)
  }.map(_.head).mkString
  println(s"Part 2: $partTwo")
}

def parseStacks(xs: List[String]): List[List[Char]] = {
  (1 to 33 by 4).map(i => xs.collect {case s if s.isDefinedAt(i) && s(i) != ' ' => s(i)}).toList
}