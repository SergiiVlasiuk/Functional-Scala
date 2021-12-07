package experiments.implicut_

import scala.Predef.{any2stringadd => _, _}
//import scala.language.implicitConversions

object ImplicitExample {
  def main(args: Array[String]): Unit = {
    val five = ImplicitWrapper(5)
//    val value = 9 + five + 9
    val value = five + 9 + five

    Int
    println(value)
    println(value.prettyString)
    println(value.prettyString("*"))
  }

  implicit def defaultQuates: String = "\""
}

//case class ImplicitWrapper[+T : Ordering](x: T){ // similar meaning
case class ImplicitWrapper[+T](x: T)(implicit ordering: Ordering[T]){ // similar meaning
  def map[R : Ordering](fn: T => R): ImplicitWrapper[R] = ImplicitWrapper(fn(x))

  def max[R >: T : Ordering](maybeMax: R): ImplicitWrapper[R] = {
    val ordering: Ordering[R] = implicitly[Ordering[R]] // todo! PA: takes implicit value to use in the function directly
    //    if (ordering.compare(x, maybeMax) >= 0) this.map(_.asInstanceOf[R]) // huck,
    if (ordering.compare(x, maybeMax) >= 0) this
    else ImplicitWrapper(maybeMax)
  }
  //  def self(param: T): T = param

  def prettyString(implicit quote: String) = s"$quote$x$quote"
}
object ImplicitWrapper {
  implicit class IntRichWrapper(val w: ImplicitWrapper[Int]) extends AnyVal {
    def +(num: Int): ImplicitWrapper[Int] = ImplicitWrapper(w.x + num)
    def +(num: ImplicitWrapper[Int]): ImplicitWrapper[Int] = ImplicitWrapper(w.x + num.x)
  }
//  implicitly
//    implicit def intWrapper2Int(intWrapper: ImplicitWrapper[Int]): Int = intWrapper.x
}
