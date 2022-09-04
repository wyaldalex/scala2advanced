package lectures.part1afp

import scala.annotation.tailrec

object RecursiveFactorialAgain  extends App {

  def tailRecursiveFactoria(n: Int): Int = {
    @tailrec
    def go(n: Int, accumulator: Int): Int = {
      //base case
      if (n <= 1) n * accumulator
      else go(n-1,n*accumulator)
    }
    go(n,1)
  }

  println(tailRecursiveFactoria(5))

}
