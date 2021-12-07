package lections.week1

import scala.annotation.tailrec

// square root

object lecture_1_5 {

  def main(args: Array[String]) {
    println(s"square root")

    println(s"sqrtIter: ${sqrt(6)}")
    println(s"sqrtIter: ${sqrt(4)}")
    println(s"sqrtIter: ${sqrt(1)}")
    println(s"sqrtIter: ${sqrt(1e-6)}")
    println(s"sqrtIter: ${sqrt(1e60)}")
  }

  def sqrt (x: Double) = sqrtIter(1.0, x)

  @tailrec
  def sqrtIter(guess: Double, x: Double): Double =
    if (isGoodEnough(guess, x)) guess
    else sqrtIter(improve(guess, x), x)

  def isGoodEnough(guess: Double, x: Double): Boolean =
    abs(guess * guess - x) / x < 0.001
//    abs(guess * guess - x) < 0.001

  def improve(guess: Double, x:Double): Double =
    (guess + x / guess) / 2

  def abs(x: Double): Double = if (x < 0) -x else x
}
