package lections.week2

object Rational {
  def main(args: Array[String]): Unit = {
    val x: Rational = new Rational(1,3)
    val y: Rational = new Rational(5,7)
    val z: Rational = new Rational(3,2)

    println(s"x.add(y).mul(z): ${x.add(y).mul(z)}")
    println(s"x.sub(y).sub(z): ${x.sub(y).sub(z)}")
    println(s"x.number: ${x}")
    println(s"x.add(y): ${x.add(y)}")
  }
}


case class Rational(x: Int, y: Int) { //the constructor
  require(y!=0, "denominator must be nonzero")
  //to guard against users creating illegal Rational with a zero denominator

  def this(x: Int) = this(x,1) //a secondary constructor

  @scala.annotation.tailrec
  private def gcd(a: Int, b: Int): Int = if(b==0) a else gcd(b, a % b)
  private def abs(a: Int): Int = if(a>=0) a else -a
  private def g: Int = abs(gcd(x,y))

  //We want to simplify Ints before we perform operations because the limit of ints
  //are just about 2 billion. We don't want to get into arithemetic overflows early
  val numer: Int = x / g
  val denom: Int = y / g
//  def numer = x
//  def denom = y

  //ADDITION
  def add(that: Rational): Rational = {
    new Rational(
      this.numer*that.denom + that.numer*this.denom,
      this.denom*that.denom
    )
  }
  def mul(r: Rational): Rational = Rational(numer * r.denom + r.numer * denom, denom * r.denom)
  def + (that: Rational): Rational = {
    new Rational(
      this.numer*that.denom + that.numer*this.denom,
      this.denom*that.denom
    )
  }
  //NEGATE
  def neg: Rational = new Rational(-numer, denom)
  def unary_- :Rational = new Rational(-numer, denom) //prefix operation

  //SUBTRACTION
  def sub(that: Rational): Rational = add(that.neg)

  def - (that: Rational): Rational = this + -that


  //COMPARISON
  def less(that: Rational): Boolean = {
    this.numer*that.denom < that.numer*this.denom
  }
  def < (that: Rational): Boolean = {
    this.numer*that.denom < that.numer*this.denom
  }

  //MAX
  def max(that: Rational): Rational = {
    if(this.less(that)) that else this
  }
  def min(s: Rational): Rational = if (s.less(this)) s else this
  def abs: Rational = Rational(this.numer.abs, this.denom)

  def max2(that: Rational): Rational = {
    if(this < that) that else this
  }
  //A toString method of a class will always be called whenever we println
  override def toString: String = numer + "/" + denom
}
