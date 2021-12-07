package lections.week1

import scala.annotation.tailrec

object lecture_1_7 {

  def main(args: Array[String]): Unit = {
    println(s"gcd(36, 63): ${gcd(36, 63)}")
  }

  @tailrec
  def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
}
