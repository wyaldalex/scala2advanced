package lectures.part3afp

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Success

object Monads2 extends App {

  //Monads wrap values into a more complex interesting types
  case class SafeValue[+T] (private val internalValue: T) {
    def get: T = synchronized {
      internalValue //this is the equivalent of Unit in a Monad
    }

    def transform[S](transformer: T => SafeValue[S]) : SafeValue[S] = synchronized {
      transformer(internalValue) //this function is the equivalent of a flatMap/bind in a Monad
    }
  }

  //external API
  def gimmeSafeValue[T](value: T): SafeValue[T] = SafeValue(value)

  val safeString: SafeValue[String] = gimmeSafeValue("Scala is awesome")
  //extract
  val stringValue = safeString.get
  //transform
  val transformedValue = stringValue.toUpperCase()
  //wrap again
  val safeTransformedValue : SafeValue[String]  = gimmeSafeValue(transformedValue)
  //ETW pattern
  println(safeTransformedValue)

  val upperSaferString2 = safeString.transform(x => {
    SafeValue(x.toUpperCase() + " 102010210")
  })
  println(upperSaferString2)


  //More Monad Examples
  case class User(id: String)
  case class Product(sku: String, price: Double)

  def getUser(url: String): Future[User] = Future {
    Thread.sleep(1000)
    User("121-1313121U")
  }

  def getLastOrder(userId: String) : Future[Product] = Future {
    Thread.sleep(1000)
    Product("121-13131", 99.99)
  }
  val someUrl = "https://store/product?id=12121"

  val vatInclPrice : Future[Double] = getUser(someUrl)
    .flatMap(user => getLastOrder(user.id))
    .map(_.price*1.20)

  vatInclPrice.onComplete{
    case Success(value) => println(s"Result is $value")
    case _ => println("Transaction failed")
  }


  //prop 1 - left Identity
  def twoConsecutive(x : Int) = {List(x, x + 1)}
  //these two yield the same
  //Monad(x).flatMap(f) = f(x)
  println(twoConsecutive(3))
  println(List(3).flatMap(twoConsecutive))

  //prop 2 - right Identity Monad(x).flatMap(f) = Monad(X)
  println(List(1,3,3).flatMap(x => List(x)))

  //prop 3 - Associativity ETW-ETW
  val incrementer = (x: Int) => List(x,x+1)
  val doubler = (x: Int) => List(x,2*x)
  val numbers = List(1,100,1000,10000)
  println(numbers.flatMap(incrementer).flatMap(doubler) == numbers.flatMap(x => incrementer(x).flatMap(doubler)))
  //List(1,2,2,4 2,4,3,6 3,6,4,8)
  /*
  List(
  incrementer(1).flatMap(doubler) ---- 1,2,2,4
  incrementer(2).flatMap(doubler) ---- 2,4,3,6
  incrementer(3).flatMap(doubler) ---- 3,6,4,8
  )
  Monad(v).flatMap(f).flatMap(g) == Monad(v).flatMap(x => f(x).flatMap(g))
   */

  Thread.sleep(6000)



}
