package lectures.recap

import scala.annotation.tailrec

object RecursionRecap extends App {

  def quickFiboNumber (n: Int): Int = {
    @tailrec
    def go(counter: Int,currentNumber: Int ,prevNumber: Int): Int = {
      if (counter == n) prevNumber
      else go(counter+1, currentNumber + prevNumber, currentNumber)
    }
    go(1,1,1)
  }
  println(quickFiboNumber(7))

  def quickFactorial (n: Int) : Int = {

    @tailrec
    def go(counter: Int, acc: Int): Int = {
      if(counter==1) counter * acc
      else go(counter -1, counter * acc)
    }
    go(n,1)
  }

  println(quickFactorial(5))

}
