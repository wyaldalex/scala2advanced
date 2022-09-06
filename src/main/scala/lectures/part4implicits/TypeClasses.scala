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

object TypeClassesPart2 extends App {

  trait HTMLSerializerAll[T] {
    def serialize(value: T): String
  }

  case class User(id: Int, name: String, email: String)

  object HTMLSerializer {
    def serialize[T](value: T)(implicit serializer: HTMLSerializerAll[T]): String =
      serializer.serialize(value)

    //add this apply to have access to the syntax serializer[someType].serialize()
    def apply[T](implicit serializer: HTMLSerializerAll[T]) = serializer
  }

  implicit object IntSerializer extends HTMLSerializerAll[Int] {
    override def serialize(value: Int): String = s"<div sytle: color=blue>$value</div>"
  }

  implicit object UserSerializer1 extends HTMLSerializerAll[User] {
    override def serialize(value: User): String = s"<div sytle: color=blue>${value.name} and ${value.email}</div>"
  }
  val someUser = User(1212, "Esther", "esda@gmail.com")

  println(HTMLSerializer.serialize(100))
  println(HTMLSerializer.serialize(someUser))

  println(HTMLSerializer[User].serialize(someUser))

}

object TypeClassPart2Exercise extends App {

  case class User(id: Int, name: String, email: String)

  val someUser = User(1212, "Esther", "esda@gmail.com")
  val someUser2 = User(1212, "Esther", "esda2@gmail.com")
  val someUser3 = User(1212, "Esther", "esda@gmail.com")
  //1) start with a trait
  trait Equalizer[T] {
    def euqualize(a: T, b: T) : Boolean
  }
  //2) create the singleton that needs to extend the trait also with generics
  object Equalizer {
    def euqualize[T](a: T, b: T)(implicit equalizer: Equalizer[T]) : Boolean
    = equalizer.euqualize(a,b)

    //create apply to have access to the instance approach and have access to al methods
    def apply[T](implicit equalizer: Equalizer[T]) = equalizer
  }

  //3) create the concrete implicit implementations
  implicit object UserEqualizer extends Equalizer[User] {
    override def euqualize(a: User, b: User): Boolean = {
      if(a.name == b.name && a.email == b.email) true
      else false
    }
  }

  //4) call using the instance approach
  println(Equalizer[User].euqualize(someUser,someUser2))
  println(Equalizer[User].euqualize(someUser,someUser3))

}