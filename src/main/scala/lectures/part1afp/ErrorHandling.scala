package lectures.part1afp

import scala.util.{Failure, Success, Try}

object ErrorHandlingTry extends App {

  def intToString(stringNumbs: List[String]) =  {
    //try catches non fatal exceptions
    stringNumbs.map(x => Try(x.toDouble))
  }

  val returnTries = intToString(List("adsa12.3","12121","adasdas","124.45","1212","xxxx"))
  returnTries.foreach(x => println(x))

  //use only succesful values
  val succesfulTries = returnTries.filter(x => x.isInstanceOf[Success[Double]])
  val failedTries = returnTries.filter(x => x.isInstanceOf[Failure[Throwable]])

  succesfulTries.foreach(println(_))
  failedTries.foreach(println(_))

  //doing something with successes
  val total =succesfulTries.map(x => { x match {
    case Success(value) => value
    case _ => 0
  }}).reduce(_ + _)
  println(s"total: $total")

}

object UsingOptions extends App {

  //Option cant handle fails
  def intToString(stringNumbs: List[String]) = {
    //try catches non fatal exceptions
    stringNumbs.map(x => {
      try {
        Some(x.toDouble)
      } catch {
        case e:Exception => None
      }
    })
  }
  val returnTries = intToString(List("adsa12.3", "12121", "adasdas", "124.45", "1212", "xxxx"))
  returnTries.foreach(x => println(x))
}

object UsingEither extends App {

  //Option cant handle fails
  def intToString(stringNumbs: List[String]) = {
    //try catches non fatal exceptions
    stringNumbs.map(x => {
      try {
        Right(x.toDouble)
      } catch {
        case e:Exception => Left("Failed to parse value")
      }
    })
  }
  val returnTries = intToString(List("adsa12.3", "12121", "adasdas", "124.45", "1212", "xxxx"))
  returnTries.foreach(x => println(x))
}
