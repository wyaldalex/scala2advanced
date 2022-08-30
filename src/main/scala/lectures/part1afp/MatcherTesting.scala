package part1afp

object MatcherTesting extends App {

  trait Date
  trait Time
  class DateTime() extends Date with Time

  val myDateTime = new DateTime()

  myDateTime match {
    case d: Date => println("Matched a Date")
    case t: Time => println("Matched a Time")
    case _ => println("This was not a date or time")
  }

}

object AdvancedMatchers extends App {
  object specialNumber {
    def unapply(someNum: Int) : Option[String] = {
      if(someNum < 100  && (someNum % 5) == 0 && (someNum % 3) == 0) Some("The number complies")
      else None
    }
  }

  val specialNum :Int = 75
  val result = specialNum match {
    case specialNumber(_) => println("This number complies ")
    case _ => println("This number fails to comply with special conditions")
  }

}
