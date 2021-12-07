package generators

object FromUntil {
  def main(args: Array[String]): Unit = {
    for (i <- 0 until 3) {
      println(s"i = $i")
    }
    /*
i = 0
i = 1
i = 2
    */
  }

}
