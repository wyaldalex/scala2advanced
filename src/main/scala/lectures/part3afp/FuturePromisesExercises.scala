package lectures.part3afp

import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future, Promise}
import scala.util.{Failure, Random, Success}

object FuturePromisesExercisesFirstCompleted extends App {

  import CommonOperations._

  @tailrec
  def getFirstCompletedFuture(a: Future[Int], b: Future[Int]) : Future[Int] = {
    val futuresList = List(a,b)
    val completedFutures = futuresList.filter(x => x.isCompleted)
    if (!completedFutures.isEmpty) completedFutures(0)
    else getFirstCompletedFuture(a,b)
  }

  val firstCompletedFuture = getFirstCompletedFuture(
    nuclearFutureA(15000),
    nuclearFutureB(15000))

  firstCompletedFuture.onComplete{
    case Success(value) => println(s"Result of nuclear reaction $value")
    case Failure(exception) =>  exception.printStackTrace()
  }

}

object FuturePromisesExercisesLastCompleted extends App {

  import CommonOperations._

  @tailrec
  def getLastCompletedFuture(a: Future[Int], b: Future[Int]) : Future[Int] = {
    val futuresList = List(a,b)
    val incompletedFutures = futuresList.filterNot(x => x.isCompleted)
    if (incompletedFutures.size == 1) incompletedFutures(0)
    else getLastCompletedFuture(a,b)
  }

  val lastCompletedFuture = getLastCompletedFuture(
    nuclearFutureA(15000),
    nuclearFutureB(15000))

  val result = Await.result(lastCompletedFuture,20.seconds)
  println(s"Last completed future value is: $result")

}

object RetryUntil extends App {

  def retryUntil[A] (action: () => Future[A], condition: A => Boolean) : Future[A] = {
    action().filter(x => condition(x))
      .recoverWith{
        case _ => {
          println("Retrying")
          retryUntil(action, condition)
        }
      }
  }

  def someAction() : Future[Int] = Future {
    println("Generating Random Number")
    Random.nextInt(14)
  }

  def someCondition(n: Int) : Boolean = {
    if (n > 10) true
    else false
  }

  retryUntil(someAction,someCondition).foreach(result => println(s"settled at $result"  ))
  Thread.sleep(20000)


}

object CommonOperations {

  def nuclearFutureA(maxTime: Int): Future[Int] = Future {
    println("[ Reaction A ] Processing nuclear reaction")
    Thread.sleep(Random.nextInt(maxTime))
    println("[ Reaction A ] Nuclear reaction Completed")
    231
  }

  def nuclearFutureB(maxTime: Int): Future[Int] = Future {
    println("[ Reaction B ] Processing nuclear reaction")
    Thread.sleep(Random.nextInt(maxTime))
    println("[ Reaction B ] Nuclear reaction Completed")
    232
  }
}
