package lections.week2

// currying
object lecture_2_2_currying {

  def main(args: Array[String]) {
    println("currying")

    println(s"production: ${product(x => x * x)(3, 4)}")
    println(s"factorial: ${fact(5)}")
    println(s"product: ${product(x => x * x)(3, 4)}")
    println(s"product2: ${product2(x => x * x)(3, 4)}")
    println(s"sum(fact)(1, 5): ${sum(fact)(1, 5)}")
  }

  def product(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 1
    else f(a) * product(f)(a + 1, b)

  def product2(f: Int => Int)(a: Int, b: Int): Int =
    mapReduce(f, (x, y) => x * y, 1)(a, b)

  def fact(n: Int): Int = product(x => x)(1, n)

//  def mapReduce(f: Int => Int,
//                combine: (Int, Int) => Int,
//                zero: Int)(a: Int, b: Int): Int =
//    if (a > b) zero
//    else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
//  output
//  currying
//  production: 144
//  factorial: 120
//  product: 144
//  product2: 144

  def mapReduce(f: Int => Int,
                combine: (Int, Int) => Int,
                zero: Int)
               (a: Int, b: Int): Int = {
    def recur(a: Int): Int =
      if (a > b) zero
      else combine(f(a), recur(a + 1))
    recur(a)
  }

  def sum(f: Int => Int): (Int, Int) => Int = mapReduce(f, (x, y) => x + y, 0)
}
