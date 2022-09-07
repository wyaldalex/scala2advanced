package lectures.part1afp

object AdvancedPatternMatching extends App {
  import CommonStuff._

  case class ProperPerson(name: String, age: Int)

  val properPerson = new ProperPerson("Bob",50)
  val classicalPerson = new ClassicalPerson("Bob",50)

  //For case classes pattern matching works out of the box
  val result = properPerson match {
    case ProperPerson(x,b) => s"Proper person $x"
  }

  //For classical classes you need to provide the unapply method
  val result2 = classicalPerson match {
    case ClassicalPerson(x, b) => s"Classical Person $x"
  }
  val result3 = classicalPerson.age match {
    case ClassicalPerson(x) => s"Legal status is $x"
  }
  println(result)
  println(result2)
  println(result3)


}

object PatternMatching2 extends App {

  val numbers = List(1)

  val description = numbers match {
    case head :: Nil => s"The list has only one element $head"
    case _ => "Something else"
  }
  println(description)

  //infix patterns
  case class Or[A,B](a: A, b: B)
  val either = Or(1,"One")
  val humanDescription = either match {
    case x31 Or adb => s"$x31 from the first arg and $adb from the second one"
  }
  println(humanDescription)

}

object AdvancedPatternMatching2Decompose extends App {

  //Creating your own functional List
  abstract class MyList[+A] {
    def head: A = ???
    def tail: MyList[A] = ???
  }
  case object Empty extends MyList[Nothing]
  case class Cons[+A] (
                      override val head: A,
                      override val tail: MyList[A]
                      ) extends MyList[A]

  object MyList {
    def unapplySeq[A](list: MyList[A]) : Option[Seq[A]] = {
      
      if (list == Empty) Some(Seq.empty)
      else unapplySeq(list.tail).map(list.head +: _)
    }
  }

  val myList : MyList[Int] = Cons(1,Cons(2,Cons(3, Cons(4,Cons(5,Empty)))))
  val decompose = myList match {
    case MyList(1,2,3,_*) => "starting with 1,2 and 3"
    case _ => "Something else"
  }
  println(decompose)

  //Default list also has this functionality by default....
  //Comparison with the normal List
  val someNormalList = List(1,2,3,4,5)
  val decompose2 = someNormalList match {
    case List(1,2,3,_*) => "starting with 1,2 and 3"
    case _ => "Something else"
  }
  println(decompose2)

}

//Return custom types with unapply
object CustomTypes extends App {
  import CommonStuff._

  abstract class Wrapper[T] {
    def isEmpty = false
    def get: T
  }
  class WrapperString(name: String) extends Wrapper[String] {
    override def isEmpty: Boolean = false
    override def get  = name
  }

  object PersonWrapper {
    def unapply(person: ClassicalPerson) : Wrapper[String] = new WrapperString(person.name)
  }

  val classicalPerson = new ClassicalPerson("Bob",50)
  val description = classicalPerson match {
    case PersonWrapper(n) => s"Hey my name is $n"
    case _ => "I am an alien"
  }
  println(description)


}

object CommonStuff {
  class ClassicalPerson(val name: String, val age: Int)

  //normal classes require the unapply in the companion object
  //object name does need to be the same in this case but is usually done in the companion
  object ClassicalPerson {
    def unapply(p: ClassicalPerson): Option[(String, Int)] = {
      if (p.age > 21) Some(p.name -> p.age)
      else None
    }

    def unapply(age: Int): Option[(String)] = {
      Some(if (age > 21) "major" else "minor")
    }
  }
}

