import scala.annotation.nowarn
import scala.util.Using
import scala.io.Source

@main @nowarn def main = {
  val input = Using.resource(Source.fromFile("input-day5.txt"))(_.getLines.toList)
  val stacks = parseStacks(input)
  val moves = parseMoves(input)
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
  val stackCount = xs.collectFirst{case ln if ln.last.isDigit => ln.split(' ').last.toInt - 1}.getOrElse(0)
  val stackLines = xs.takeWhile(_.last == ']')
  (1 to (stackCount * 4 + 1) by 4).map(i => stackLines.collect {case s if s.isDefinedAt(i) && s(i) != ' ' => s(i)}).toList
}

def parseMoves(xs: List[String]): List[Array[Int]] = {
  val moveStrings = xs.dropWhile(!_.startsWith("move"))
  moveStrings.map(_.split(' ').collect {case x if x.forall(_.isDigit) => x.toInt}).filter(_.length == 3)
}
