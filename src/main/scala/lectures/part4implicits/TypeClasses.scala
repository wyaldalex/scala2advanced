package lectures.part4implicits

object TypeClasses extends App {


  case class User(id: Int, name: String, email: String)

  trait HTMLSerializerAll[T] {
    def serialize(value: T): String
  }

  trait Equalizer[T] {
    def equals(a: T, b: T): Boolean
  }

  object UserHTMLSerializer extends HTMLSerializerAll[User] {
    override def serialize(user: User): String =
      s"<div> id:${user.id} with name ${user.name} and email ${user.email} </div>"
  }

  object UserEqualizer extends Equalizer[User] {
    override def equals(a: User, b: User): Boolean = {
      if (a.name == b.name && a.email == b.email) true
      else false
    }
  }

  val someUser = User(1212, "Esther", "esda@gmail.com")
  val someUser2 = User(1212, "Esther", "esda2@gmail.com")
  val someUser3 = User(1212, "Esther", "esda@gmail.com")
  println(UserHTMLSerializer.serialize(someUser))
  println(UserEqualizer.equals(someUser, someUser2))
  println(UserEqualizer.equals(someUser, someUser3))

}
