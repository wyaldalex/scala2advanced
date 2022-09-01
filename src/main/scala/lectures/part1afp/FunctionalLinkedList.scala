package lectures.part1afp

import lectures.part1afp.FunctionalLinkedList.MyList.sum

object FunctionalLinkedList extends App {

  sealed trait MyList[+A]
  case object Nil extends MyList[Nothing]
  case class Cons[+A](head: A, tail: MyList[A]) extends MyList[A]

  object MyList {
    def sum(ints: MyList[Int]) : Int = ints match {
      case Nil => 0
      case Cons(x,xs) => x + sum(xs)
    }

    def dropWhile[A](as: MyList[A])(f: A => Boolean) : MyList[A] =
      as match {
        case Cons(h,t) if f(h) => dropWhile(t)(f)
        case _ => as
      }

    def append[A] (a1: MyList[A], a2: MyList[A]) : MyList[A] = a1 match {
      case Nil => a2
      case Cons(h,t) => Cons(h,append(t,a2))
    }


    /*
    Scala has a special rule for this method name, so that
    objects that have an apply method can be called as if they were themselves methods. When we define a function literal like (a, b) => a < b, this is really syntactic
    sugar for object creation:
    val lessThan = new Function2[Int, Int, Boolean] {
    def apply(a: Int, b: Int) = a < b
    }
    The function apply in the object List is a variadic function, meaning it accepts zero
    or more arguments of type A:
     */
    def apply[A](as: A*) : MyList[A] =
      if(as.isEmpty) Nil
      else Cons(as.head, apply(as.tail: _*))
  }

  val someList = MyList(1,3,3,5)
  println(sum(someList))


}
