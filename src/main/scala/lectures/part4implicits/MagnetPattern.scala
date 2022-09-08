package lectures.part4implicits

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object MagnetPattern extends App {

  case class P2PRequest(body: String)
  case class P2PResponse(body: String)

  trait MessageMagnet[R] {
    def apply() : R
  }

  def receive[R](magnet: MessageMagnet[R]) : R = magnet()

  implicit class FromP2PRequest(request: P2PRequest) extends MessageMagnet[Int] {
    override def apply(): Int = {
      println("Handling P2P request")
      42
    }
  }

  implicit class FromP2PRequestFuture(future: Future[P2PRequest]) extends MessageMagnet[String] {
    override def apply(): String = {
      println("Handling Future P2PRequest")
      "Some string response"
    }
  }

  println(receive(P2PRequest("{id: 121}")))
  println(receive(Future(P2PRequest("{id: 121}"))))

}
