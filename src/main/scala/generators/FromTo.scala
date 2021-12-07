package generators

object FromTo {
  def main(args: Array[String]): Unit = {
    for {
      i <- 0 to 3
    }
    {
      println(s"i = $i")
    }

    val res = for {
      i <- 0 to 3
    } yield i
    println(s"res: $res")
    /*
i = 0
i = 1
i = 2
i = 3
   */
  }

}
