package lectures.part4implicits

import cats.effect.IO

object IOrelated extends App {

  case class Player(name: String, score: Int)

  def winner(p1: Player, p2: Player) : Option[Player] = {
    if (p1.score > p2.score) Some(p1)
    else if (p1.score < p2.score) Some(p2)
    else None
  }

  def winnerMsg(p: Option[Player]) : String = p map {
    case Player(name,_) => s"name of the winner is $name !!!"
  } getOrElse "Its a draw!"

  def contest(p1: Player, p2: Player) : Unit = {
    println(winnerMsg(winner(p1,p2)))
  }

  //Testing the pure functional approach
  val player1 = Player("Ultron",123)
  val player2 = Player("Megatron",200)
  contest(player1,player2)

}

//Referential Transparency and the IO monad
object IOMonadTesting extends App {
  //reference : https://medium.com/walmartglobaltech/understanding-io-monad-in-scala-b495ca572174
  def demo(f1: Unit, f2: Unit): Unit = {
    ()
  }
  val x = println("Something")
  //The two below are not equal, breaking referential transparency
  demo(x,x)
  demo(println("Something"),println("Something"))

  /*
  The solution to our problem comes with the ability to control WHEN these effects get evaluated.
  This is where the IO Monad comes in. The IO Monad can be thought of as a wrapper around these effects, which provides us the ability to evaluate them when we need to. We’ll be using the cats-effect library in this post to demonstrate the use of IO Monad.
   */
  //eagerly  evaluated
  def classicSideEffect(str: String): Unit = {
    println(str)
  }
  classicSideEffect("Something 2")

  //Using the IO Monad
  def ioMonadSideEffect(str: String): IO[Unit] = IO {
    println(str)
  }
  val ioMonadResult = ioMonadSideEffect("Something 2")
  ioMonadResult.unsafeRunSync()

  //now we should see it printed two times too
  demo(ioMonadResult.unsafeRunSync(),ioMonadResult.unsafeRunSync())

}
