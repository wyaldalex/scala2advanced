package lectures.recap

import lectures.common.Tanks._

import scala.annotation.tailrec

object HOFuctionsExercises extends App {


  //create a function that takes a function to convert tank cannon barrel
  // to other measurements
  //should return a list of Doubles
  //is the function pure?
  def convertBarrelSpecs (tanks: List[Tank], f: Double => Double) : List[Double] = {
    //helper go/loop
    @tailrec
    def go(counter: Int, accumulatorList: List[Double]): List[Double] ={
      //trivial/base case
      if(counter == tanks.size - 1) accumulatorList
      else go(counter + 1, accumulatorList :+ f(tanks(counter).cannonMM))
    }

    go(0,List.empty)
  }

  //cannonBarrelsInInches
  val tankBarrelsInInches = convertBarrelSpecs(tankList, (x: Double) => x/25.4)
  tankBarrelsInInches.foreach(println(_))

  val tankBarrelsCentimeters = convertBarrelSpecs(tankList, (x: Double) => x / 10)
  tankBarrelsCentimeters.foreach(println(_))
}

//recurisve calculate time to drain tank, drain rate is provided
object DrainTank extends App {

  def calculateTimeToDrainTank[A](amount: A, f: A => Double) : Double = {
    f(amount)
  }
  def centimetersDrainRate(amount: Double): Double = {
    @tailrec
    def go(remainingAmount: Double, timeSeconds: Int): Double = {
      if(remainingAmount <= 0) timeSeconds
      else go(remainingAmount - 45, timeSeconds + 1)
    }
    go(amount,0)
  }

  val inCentimetersVolumeDrainTime = calculateTimeToDrainTank(20000.56,centimetersDrainRate)
  println(inCentimetersVolumeDrainTime)

}


