package lections.week2

// rationals
object lecture_2_5 {

  def main(args: Array[String]) {
    println("rationals")
    val x: Rational = new Rational(1,3)
    val y: Rational = new Rational(5,7)
    val z: Rational = new Rational(3,2)

    println(s"x.number: ${x}")
    println(s"x.add(y): ${x.add(y)}")
    println(s"x.sub(y).sub(z): ${x.sub(y).sub(z)}")
  }
}

