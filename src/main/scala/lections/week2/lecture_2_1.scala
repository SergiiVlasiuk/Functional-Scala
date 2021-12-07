package lections.week2

import scala.annotation.tailrec

// higher order functions
object lecture_2_1 {

  def main(args: Array[String]) {
    println("higher order functions")
    println(s"sumInts: ${sumInts(3, 6)}")
    println(s"sumCubes: ${sumCubes(3, 6)}")
    println(s"sumFactorials: ${sumFactorials(3, 6)}")
  }

  def sum(f: Int => Int, a: Int, b: Int): Int =
    if (a > b) 0
    else f(a) + sum(f, a + 1, b)

  def sumInts(a: Int, b: Int): Int = sum(id, a, b)
  def sumCubes(a: Int, b: Int): Int = sum(cube, a, b)
  def sumFactorials(a: Int, b: Int): Int = sum(fact, a, b)

  def id(x: Int): Int = x
  def cube(x: Int): Int = x * x * x
  def fact(x: Int): Int = if (x == 0) 1 else x * fact(x - 1)

  def sumFromTo(f: Int => Int, from: Int, to: Int): Int = {
    @tailrec
    def loop(from: Int, acc: Int): Int =
      if (from > to) acc
      else loop(from + 1, acc + f(from))
    loop(from, 0)
  }
}
