package part1afp

import scala.math.sqrt

object AnonAndHOFuncs extends App {

  val someAnonFunc = (x : Double) => {
    sqrt(x) + 100 / 7.5
  }

  val someListOfNumbers = List(1,4,5,1,21,312,3,13,123)
  someListOfNumbers.map(x => someAnonFunc(x)).foreach(println)


  def factorianN(n: Int) : Int = {
    def go(n: Int, acc: Int) : Int = {
      if (n <= 1) acc
      else go(n-1, acc*n)
    }
    go(n, 1)

  }

  def formatResult(operation:String , n: Int, f: Int => Int ): String ={
    val msg = "The formatted %s output of %d is %d"
    msg.format(operation,n,f(n))
  }
  println( formatResult("factorial",5, factorianN))

}

object PartialFuncsTesting extends App {

  def partial1[A,B,C](a: A,f: (A,B) => C) : B => C =
    (b: B) => f(a,b)

  def curry[A,B,C](f: (A,B) => C) : A => (B => C) =
    (a: A) => f(a, _)
}
