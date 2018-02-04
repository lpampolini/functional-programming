package Exercises

object PolymorphicSort{

  def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {
    @annotation.tailrec
    def loop(n: Int): Boolean = {
      if (n == as.length-1) true
      else if (!ordered(as(n), as(n+1))) false
      else loop(n+1)
    }
    loop(0)
  }

  def compareIntegers(num1: Int, num2: Int): Boolean = {
    if (num1 <= num2) true
    else false
  }

  def compareFloats(flt1: Double, flt2: Double): Boolean = {
    if (flt1 <= flt1) true
    else false
  }

  def main(args: Array[String]): Unit = {
    val unsortedFloatArray = Array(0.12, 20 , 0)
    val sortedFloatArray = Array(1.0, 1.5, 1.7)

    val unsortedNumberArray = Array(1, 5, 2)
    val sortedNumberArray = Array(1, 2, 3)

    println(isSorted(unsortedFloatArray, compareFloats))
    println(isSorted(sortedFloatArray, compareFloats))
    println(isSorted(unsortedNumberArray, compareIntegers))
    println(isSorted(sortedNumberArray, compareIntegers))
  }

}