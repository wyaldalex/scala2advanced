package lectures.part4implicits

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
