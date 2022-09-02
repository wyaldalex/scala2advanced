package lectures.part3afp

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future, Promise}
import scala.util.{Failure, Random, Success}

object FutureConcurrency3 extends App {

  import BankingApp._
  println(BankingApp.purchase("Sarah","Iphone 12", "RockTheJVM",3000))
}

object FutureConcurrency3Promises extends App {

  val promise = Promise[Int]()
  val future = promise.future

  //thread 1 - consumer
  future.onComplete{
    case Success(value) => println("[consumer] I've received "+ value)
  }

  //thread 2 - producer
  val producer = new Thread(() => {
    println("[producer] crunching numbers...")
    Thread.sleep(500)
    promise.success(42)
    println("[producer] done")
  })

  producer.start()
  Thread.sleep(1000) //Still the main thread had to wait somewhere

}


object BankingApp {
  //online banking up
  case class User(name: String)
  case class Transaction(sender: String, receiver: String, amount: Double, status: String)

  val name = "Rock the JVM banking"

  def fetchUser(name: String) : Future[User] = Future {
    Thread.sleep(500)
    User(name)
  }

  def createTransaction(user: User, merchantName: String, amount: Double) : Future[Transaction] = Future {
    Thread.sleep(1000)
    Transaction(user.name, merchantName, amount, "SUCCESS")
  }

  def purchase(userName: String, item: String, merchanteName: String, cost: Double) : String = {
    val transactionStatusFuture = for {
      user <- fetchUser(userName)
      transaction <- createTransaction(user,merchanteName,cost)
    } yield transaction.status

    Await.result(transactionStatusFuture,2.seconds)

  }

}

