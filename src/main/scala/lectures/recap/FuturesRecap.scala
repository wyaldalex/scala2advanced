package lectures.recap

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Random, Success}

object FuturesRecap extends App {

  case class Player(id: Int, name: String, kills: Int, level: Double)

  def updatePlayerLevel(player: Player) : Future[Player] = Future {
    //do some api service call
    Thread.sleep(Random.nextInt(20000)) //Not really a pure function as its simulating the call to another
    player.copy(level = player.level + 20)
  }

  val somePlayers = List(
    Player(1,"xor",9000,78),
    Player(2,"xar",7000,65),
    Player(3,"trop",6000,78),
    Player(4,"jok",19000,85),
    Player(5,"zer",90000,99),
  )

  val modifiedPlayers = somePlayers.map(updatePlayerLevel)
  val modifiedPlayersSequenced = Future.sequence(modifiedPlayers)

  val usableModifiedPlayers = Await.result(modifiedPlayersSequenced,100.seconds)
  usableModifiedPlayers.foreach(
    x=> println(x.level)
  )










}
