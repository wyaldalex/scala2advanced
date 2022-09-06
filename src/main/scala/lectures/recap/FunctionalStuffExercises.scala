package lectures.recap

import lectures.recap.FunctionalDataStructures.XList.sum

object FunctionalDataStructures extends App {

  sealed trait XList[+A]
  case object Nil extends XList[Nothing]
  case class Cons[+A](head: A, tail: XList[A]) extends XList[A]

  object XList {
    def sum(ints: XList[Int]): Int = ints match {
      case Nil => 0
      case Cons(x,xs) => x + sum(xs)
    }
    def product(ds: XList[Double]): Double = ds match {
      case Nil => 1.0
      case Cons(0.0, _) => 0.0
      case Cons(x,xs) => x * product(xs)
    }
    def apply[A](as: A*): XList[A] =
      if (as.isEmpty) Nil
      else Cons(as.head, apply(as.tail: _*))
  }

  //using the constructor class
  val usingConsList = Cons(1,Cons(3,Cons(4,Nil)))
  println(usingConsList.tail)
  println(sum(usingConsList))

  def tail[A](l: XList[A]): XList[A] =
    l match {
      case Nil => sys.error("tail of empty list")
      case Cons(_, t) => t
    }

  println(tail(XList(1, 2, 3)))
  println(tail(XList(1)))

  def setHead[A](l: XList[A], h: A): XList[A] =
    l match {
      case Nil => sys.error("setHead on empty list")
      case Cons(_, t) => Cons(h, t)
    }
  println(setHead(XList(1, 2, 3), 3))
  println(setHead(XList("a", "b"), "c"))


  def drop[A](l: XList[A], n: Int): XList[A] =
    if (n <= 0) l
    else
      l match {
        case Nil => Nil
        case Cons(_, t) => drop(t, n - 1)
      }

  println(
    drop(XList(1, 2, 3), 1))

  def dropWhile[A](l: XList[A], f: A => Boolean): XList[A] =
    l match {
      case Cons(h, t) if f(h) => dropWhile(t, f)
      case _ => l
    }

  println("----Testing Drop While-----")
  println(dropWhile(XList(1, 2, 3), (x: Int) => x < 2))
  println(dropWhile(XList(1, 2, 3), (x: Int) => x > 2))
  println(dropWhile(XList(1, 2, 3), (x: Int) => x > 0))
  println(dropWhile(Nil, (x: Int) => x > 0))

  def init[A](l: XList[A]): XList[A] =
    l match {
      case Nil => sys.error("init of empty list")
      case Cons(_, Nil) => Nil
      case Cons(h, t) => Cons(h, init(t))
    }

  println("----Testing Init-----")
  println(init(XList(1, 2, 3)))
  println(init(XList(1)))

  def foldRight[A, B](as: XList[A], z: B)(f: (A, B) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  def sum2(ns: XList[Int]) =
    foldRight(ns, 0)((x, y) => x + y)

  def product2(ns: XList[Double]) =
    foldRight(ns, 1.0)(_ * _)

  def map[A, B](l: XList[A])(f: A => B): XList[B] =
    foldRight(l, Nil: XList[B])((h, t) => Cons(f(h), t))

  println("----Testing foldRight-----")
  println(foldRight(XList(1, 2, 3), Nil: XList[Int])(Cons(_, _)))
}

object PureFuctionsTesting extends App {
  def sum(list: List[Int]): Int = list match {
    case Nil => 0
    case head :: tail => head + sum(tail)
  }

  def sum2(list: List[Int]): Int = list match {
    case Nil => 0
    case list: List[Int] => list.head + sum(list.tail)
  }

  println(sum(List(9,7,8)))
  println(sum2(List(9,7,8)))
}
