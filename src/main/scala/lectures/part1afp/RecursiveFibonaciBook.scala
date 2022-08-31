package lectures.part1afp

import scala.annotation.tailrec

object RecursiveFibonaciBook extends App {


  def getNFibonacciNumber(n: Int): Int = {
    require(n > 0)
    @tailrec
    def go(
           counter: Int,
           numberAtN : Int,
           prevNumber: Int ) : Int = {
      if (n == counter)  prevNumber
      else go(counter + 1, numberAtN + prevNumber, numberAtN)
    }
    go(1,1,1)
  }

  println(getNFibonacciNumber(7))

}
