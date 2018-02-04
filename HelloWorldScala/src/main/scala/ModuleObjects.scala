object ModuleObjects {

  /* That's how loops are usually implemented on FP. We have a recursive function called loop() or go() that is called
  * recursively until we reach the amount of times the loop needs to be executed */
  def factorial (n: Int): Int = {
    /* We can include this annotation to tell the compiler to attempt removing a tail call. By default, when we have
    a call in tail position it is compiled  into iterative loops that don`t consume call stack frames */
    @annotation.tailrec
    def go(n: Int, acc: Int): Int =
      if (n <= 0) acc
      /* We say this call is in tail position because it does nothing else than just simply call the function. If we
       * had something like 1 + go(n-1, n*acc), it would no longer be in tail position since it still has some work
        * to do when the go returned */
      else go(n-1, n*acc)
    go(n, 1)
  }

  def main(args: Array[String]): Unit =
    println(factorial(5))
}
