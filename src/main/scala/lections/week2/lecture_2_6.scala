package lections.week2

// rationals
object lecture_2_6 {

  def main(args: Array[String]): Unit = {
    println("rationals")
    val x: Rational = new Rational(1,3)
    val y: Rational = new Rational(5,7)
    val z: Rational = new Rational(3,2)

    println(s"x.number: ${x}")
    println(s"x.add(y): ${x.add(y)}")
    println(s"x.sub(y).sub(z): ${x.sub(y).sub(z)}")
    println(s"y.add(y): ${y.add(y)}")
    println(s"x.less(y): ${x.less(y)}")
    println(s"x.max(y): ${x.max(y)}")
  }
}

