package lectures.part1afp

import scala.annotation.tailrec

object RecursionReinforcement extends App {

  //Non tail recursive factorial
  //This one causes stackoverflow issue
  def factorial(n: Int) : Int = {
    if(n <=1 ) 1
    else {
      println("Computing factorial of " + n + "- I first head factorial of " + (n-1))
      val result = n*factorial(n-1)
      println("Computed factorial " + n)
      result
    }
  }

  def tailRecursiveFactorial(n: Int) : BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt) : BigInt = {
      if(x <= 1) accumulator
      else factHelper(x - 1, x* accumulator) //For Tail Recursion, always make the recursive call as the last expression
    }
    factHelper(n,1)
  }
  //println(factorial(5000)) //Causes Stackoverflow
  println(tailRecursiveFactorial(5000)) //Works fine

}

//Recursion exercises
object RecursionExercises extends App {

  //1) Concatenate a string a n number of times
  def nConcatenation(x: String, times: Int): String = {

    //helper Function
    //use annotation to ensure tail recursion
    @tailrec
    def runConcatenation(x: String, times: Int): String = {
      if (0 >= times) x
      else runConcatenation(x.concat(x), times-1)
    }
    runConcatenation(x,times)
  }
  println(nConcatenation("1234--x--",2))

  //2) define if a number is prime recursively
  def isNPrime(number: Int): Boolean = {

    //helper function with tailrec annotation
    @tailrec
    def isNPrimerHelper(number: Int, divisor: Int): Boolean = {
      //define the base cases
      if (divisor == 1) true //if a number reaches 1 its prime
      else if(number % divisor == 0) false //if the remainder is 0 is not prime
      else isNPrimerHelper(number, divisor -1) //loop till you either get false or reach 1
    }

    isNPrimerHelper(number,number-1)
  }
  println(isNPrime(7))
  println(isNPrime(33))
  println(isNPrime(77))
  println(isNPrime(83))
  println(isNPrime(23))

  //3) Fibonacci function, tail recursive fashion
  def tailRecFibonaci(tillFiboNumber: Int): List[Int] = {
    @tailrec //make sure tailrec validation is passing
    def tailRecFibonaciHelper(tillFiboNumber: Int, accumulatorList: List[Int], counter: Int,
                              numberToAppend: Int, prevNumber: Int): List[Int] = {
      //define base case
      if (counter == tillFiboNumber) accumulatorList
      else tailRecFibonaciHelper(
        tillFiboNumber,
        accumulatorList :+ numberToAppend ,
        counter+1,
        numberToAppend + prevNumber,
        numberToAppend
      )

    }
    tailRecFibonaciHelper(tillFiboNumber,List(1),1,1,1)
  }
  println(s"Testing fibo sequence ${tailRecFibonaci(12)}")

}

