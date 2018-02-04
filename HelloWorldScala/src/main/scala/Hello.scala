/* We are declaring a singleton object. A class that has only a single named instance */
/* Since there is no static type in scala, object is usually used to refer this type of method/object */
object MyModule {

  /* We can pronounce the colon as "has type". E.g. n has type Int */
  def abs(n: Int): Int = {
    /* This method will take an integer argument 'n' and return an integer result*/
    if (n < 0) -n /* By simply declaring -n, we state that we'll return the negation of the number*/
    else n
  }

  /* Scala can usually infer that is the type of data returned, but it's good practice to always declare it*/
  private def formatAbs(x: Int): String = {
    val message = "The absolute value of %d is %d" /* val can be used for constants */
    message.format(x, abs(x)) /* We format the message replacing the place holders %d by the number and its negation*/
  }

  /* This is the default declaration for main methods
  * the keyword Unit can also be replaced by the symbol (). Pure functions always return something.
  * Having a function that returns Unit usually indicates that it has side effects (also called procedure)
  * */

  def main(args: Array[String]): Unit = /* Unit is the same as void */
    println(formatAbs(-42))
}

/* You can compile the program by executing: "scalac Hello.scala" and then run it by executing "scala MyModule" */