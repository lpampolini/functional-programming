/* We are declaring a singleton object. A class that has only a single named instance */
/* Since there is no static type in scala, object is usually used to refer this type of method/object */
object MyModule {

  /* We can pronounce the colon as "has type". E.g. n has type Int */
  def abs(n: Int): Int = {
    /* This method will take an integer argument 'n' and return an integer result*/
    if (n < 0) -n /* By simply declaring -n, we state that we'll return the negation of the number*/
    else n
  }

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

  /* Scala can usually infer that is the type of data returned, but it's good practice to always declare it*/
  def formatResult(operation: String, n: Int, func: Int => Int): String = {
    val msg = "The %s of %s is %d" /* val can be used for constants */
    msg.format(operation, n, func(n)) /* We format the message replacing the place holders */
  }

  /* This is the default declaration for main methods
  * the keyword Unit can also be replaced by the symbol (). Pure functions always return something.
  * Having a function that returns Unit usually indicates that it has side effects (also called procedure)
  * */
  def main(args: Array[String]): Unit = /* Unit is the same as void */
    println(formatResult("absolute", -42, abs))
    println(formatResult("factorial", 7, factorial))
}

/* You can compile the program by executing: "scalac Hello.scala" and then run it by executing "scala MyModule" */