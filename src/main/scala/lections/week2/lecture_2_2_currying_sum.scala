package lections.week2

// currying
object lecture_2_2_currying_sum {

  def main(args: Array[String]) {
    println("currying")

//    println(s"production: ${product(x => x * x)(3, 4)}")
//    println(s"factorial: ${fact(5)}")
//    println(s"product: ${product(x => x * x)(3, 4)}")
//    println(s"product2: ${product2(x => x * x)(3, 4)}")
  }

/* before currying
 *  def sum(f: Int => Int, a: Int, b: Int): Int =
 *    if (a > b) 0
 *    else f(a) + sum(f, a + 1, b)
 */
  // sum with currying
  def sum(f: Int => Int): (Int, Int) => Int = {
//  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    def sumF(a: Int, b: Int): Int =
      if (a > b) 0
      else f(a) + sumF(a + 1, b)
    sumF
  }
}
