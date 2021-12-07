val tolerance = 0.0001

def abs(x: Double): Double = if (x < 0) -x else x

def isCloseEnough(x: Double, y: Double) =
  abs((x - y) / x) < tolerance

def fixedPoint(f: Double => Double)(firstGuess: Double): Double = {
  @scala.annotation.tailrec
  def iterate(guess: Double): Double = {
    val next = f(guess)
    if (isCloseEnough(guess, next)) next
    else iterate(next)
  }
  iterate(firstGuess)
}

def sqrt(x: Double) = fixedPoint(y => (y + x / y) / 2) (1.0)

//////////////////////////////////////////

val sqrt2 = sqrt(2)
val sqrt4e70 = sqrt(4e70)
