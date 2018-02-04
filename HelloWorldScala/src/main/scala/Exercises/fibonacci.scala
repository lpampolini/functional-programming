package Exercises

object fibonacci {

  def fib(n: Int): Int ={

    if (n == 0) return 0
    if (n == 1) return 1

    var previousNumber : Int = 1
    var nextSum : Int = 0

    def loop(n: Int, acc: Int): Int = {
      if (n <= 0) acc
      else {
        nextSum = acc + previousNumber
        previousNumber = acc
        loop(n - 1, nextSum)
      }
    }
    loop(n-2, 1)
  }

  def main(args: Array[String]): Unit = {
    println(fib(30))
  }

}