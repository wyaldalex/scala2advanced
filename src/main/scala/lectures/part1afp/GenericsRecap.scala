package lectures.part1afp

object GenericsRecap extends App {
/*
  class MyList[A]  {
  }
  object MyList {
    def empty[A] : MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]
  */
  //variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  //Invariant List
  class InvariantList[A]
  val animal: Animal = new Cat
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  //Covariant List
  class CovariantList[+A]
  val covariantAnimalList: CovariantList[Animal] = new CovariantList[Cat]

  //Contravariance
  class ContraviantList[-A]
  val contraviantAnimalList: ContraviantList[Cat] = new ContraviantList[Animal]
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]
  //Bounded types
  class Cage[A <: Animal](animal: A)
  //val cage = new Cage(new Dog)
  class Car
  //val cage2 = new Cage(new Car) //this fails to run because Car is not a subtype of Animal the error is not even catch

}
