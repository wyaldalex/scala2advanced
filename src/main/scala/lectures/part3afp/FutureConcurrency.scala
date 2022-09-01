package lectures.part3afp

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Random, Success}

object FutureConcurrency extends App {

  //Forget about old Threads
  //Future is what is used mostly
  def calculateNuclearReaction(): Double = {
    println("[Supercomputer ]Starting massive computation")
    Thread.sleep(6000)
    Random.nextDouble() * 1000000
  }

  val aFuture = Future {
    calculateNuclearReaction()
  }

  println("Do something else while waiting for the nuclear reaction")

  aFuture.onComplete{
    case Success(value) => println(s"The result of the computation is $value")
    case Failure(exception) => exception.printStackTrace()
  }

  Thread.sleep(10000)

}
