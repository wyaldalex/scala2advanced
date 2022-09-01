package lectures.part3afp

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Random, Success}

object FutureConcurrency2 extends App {

  case class Profile(id: String, name: String) {
    def poke(other: Profile): Unit = {
      println(s"${this.name} poking ${other.name}")
    }
  }

  object SocialNetwork {
    //database
    val names = Map(
      "fb.1.zuck" -> "Mark",
      "fb.2.bill" -> "Bill",
      "fb.3.Dummy" -> "Dummy",
    )

    val bf = Map{
      "fb.1.zuck" -> "fb.2.bill"
    }

    val random = new Random()

    //API
    def fetchProfile(id: String) : Future[Profile] = Future {
      Thread.sleep(random.nextInt(3000))
      Profile(id,names.getOrElse(id,"notfound"))
    }
    def fetchBestFriend(profile: Profile) : Future[Profile] = Future {
      Thread.sleep(random.nextInt(3000))
      val bfId = bf.getOrElse(profile.id,"")
      Profile(bfId, names.getOrElse(bfId,""))
    }
  }
  val mark = SocialNetwork.fetchProfile("fb.1.zuck")

  //Recommended approaches
  val nameOnTheMall = mark.map(profile => profile.name)
  val markBestFriend = mark.flatMap(profile => SocialNetwork.fetchBestFriend(profile))
  val zucksBestFriendRestricted = markBestFriend.filter(profile => profile.name.startsWith("Z"))
  //for-comprehensions
  for {
    mark <- SocialNetwork.fetchProfile("fb.1.zuck")
    bill <- SocialNetwork.fetchBestFriend(mark)
  } mark.poke(bill)

  Thread.sleep(10000)

  //fallbacks
  val aPrfileNoMatterWhat = SocialNetwork.fetchProfile("asdas").recover{
    case e: Throwable => Profile("robot", "robotname")
  }

  val aFetchedProfileNoMatterWhat = SocialNetwork.fetchProfile("asdas").recoverWith {
    case e: Throwable => SocialNetwork.fetchProfile("fb.1.zuck")
  }

  val fallBackResult = SocialNetwork.fetchProfile("asdas").fallbackTo(SocialNetwork.fetchProfile("fb.1.zuck"))

}


