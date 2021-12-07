package week1

import scala.annotation.tailrec
import scala.language.postfixOps

object RecursiveFunctions {

  def main(args: Array[String]) {
    println("Pascal's Triangle")
    printTriangle2(8)
  }

  def printTriangle(rowMax: Int): Unit = {
    for (row <- 0 to rowMax) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  def printTriangle2(rowMax: Int): Unit = {
    val res = for {
      row <- 0 to rowMax
      col <- 0 to row
    } yield (row, pascal(col, row))

    printTr(res.toList)

    @scala.annotation.tailrec
    def printTr(triangleValues: Seq[(Int, Int)]): Unit = {
      val (row, next) = triangleValues.span{_._1 == triangleValues.head._1}
      println(row.map(_._2).mkString(" "))
      if (next != Nil) printTr(next)
    }
//    res.toList
//        print(pascal(col, row) + " ")
//      println(res)
  }

  /**
   * The following pattern of numbers is called Pascal’s triangle.
   *     1
   *    1 1
   *   1 2 1
   *  1 3 3 1
   * 1 4 6 4 1
   *
   * The numbers at the edge of the triangle are all 1, and each number inside the triangle is the sum of the
   * two numbers above it. The function computes the elements of Pascal’s triangle by means of a recursive process.
   *
   * Function takes a column c and a row r, counting from 0 and returns the number at that spot in the triangle.
   * For example, pascal(0,2)=1, pascal(1,2)=2 and pascal(1,3)=3.
   */
  def pascal(c: Int, r: Int): Int = {
    if (c > r || c < 0) 0
    else if (c == 0) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Recursive function which verifies the balancing of parentheses in a string represented by List of Chars
   */
  def balance(chars: List[Char]): Boolean = {

    @tailrec
    def parenthesesSearch(rest: List[Char], closeStatus: Int): Boolean = rest match {
      case _ if (closeStatus < 0) => false
      case Nil => closeStatus == 0
      case head::tail => parenthesesSearch(tail, closeStatus + balanceChange(head))
    }
    def balanceChange(ch: Char): Int = ch match {
      case ')' => -1
      case '(' => 1
      case _ => 0
    }

//    def parenthesesSearch(rest: List[Char], closeStatus: Int): Boolean =
//      if (closeStatus < 0) false
//      else if (rest.isEmpty) closeStatus == 0
//      else parenthesesSearch(rest.tail, closeStatus + balanceChange(rest.head))
//
//    @tailrec
//    def parenthesesSearch(rest: List[Char], notClosed: Int): Boolean =
//      if (notClosed < 0) false
//      else if (rest.isEmpty) notClosed == 0
//      else parenthesesSearch(rest.tail, notClosed + balanceChange(rest.head))
//    def balanceChange(ch: Char): Int = if (ch == ')') -1 else if (ch == '(') 1 else 0

    parenthesesSearch(chars, 0)
  }

  /**
   * Recursive function that counts how many different ways you can make change for an amount, given a list of
   * coin denominations. For example, there are 3 ways to give change for 4 if you have coins with denomination 1
   * and 2: 1+1+1+1, 1+1+2, 2+2.
   *
   * Function takes an amount to change, and a list of unique denominations for the coins
   */
  def countChange(money: Int, coins: List[Int]): Int =
    if (money == 0) 1
    else if (money < 0) 0
    else if (coins.isEmpty) 0
    else countChange(money - coins.head, coins) + countChange(money, coins.tail)

}
