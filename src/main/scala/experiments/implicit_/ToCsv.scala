package experiments.implicit_

import java.lang.reflect.Field
import scala.annotation.tailrec
import scala.math.Ordered.orderingToOrdered

object ToCsvExample {
  def main(args: Array[String]): Unit = {
    println("======================")

    val list: List[User] = List(User("Viktoria", 18), User("Iurii", 31), User("Sergii", 99), User("D\"Antes", 300))
    println(s"list before handling: $list}")


    println(s"list as csv:\n===\n${ToCsv.toCsv(list)}")
  }
}

object ToCsv {
  def toCsv[T <: Product](list: List[T])(implicit ordering: Ordering[T]): String = {
    if (list.headOption.isEmpty) ""
    else {
      val fields: Array[Field] = list.head.getClass.getDeclaredFields
      val header               = fields.map(_.getName).mkString(",")
      val data                 = list.sorted.map(el => el.productIterator.map(_.toString).map(encode).mkString(","))
      header :: data mkString "\n"
    }
  }

  private def encode(str: String): String = {
    @tailrec
    def encodeChars(chars: List[Char], acc: List[Char] = List.empty): List[Char] = chars match {
      case Nil                      => acc
      case ch :: tail if ch == '\\' => encodeChars(tail, ch :: ch :: acc)
      case ch :: tail if ch == '\"' => encodeChars(tail, ch :: '\\' :: acc)
      case ch :: tail               => encodeChars(tail, ch :: acc)
    }
    encodeChars(str.toList).reverse.mkString("\"", "", "\"")
  }

}

case class User(name: String, age: Int) extends Ordered[User] {
  override def compare(that: User): Int = (that.name, that.age) compare (name, age)
}

//object User {
//  implicit object OrderedUser extends Ordering[User] {
//    override def compare(x: User, y: User): Int = (y.name, y.age) compare (x.name, x.age)
//  }
//}
