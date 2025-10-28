import java.io.BufferedReader
import java.io.InputStreamReader
val IN = BufferedReader(InputStreamReader(System.`in`))
fun main() {
  var xi = 0
  var xv = 0
  for (i in 1..3) {
    val v = IN.readLine()
    try {
      xv = v.toInt()
      xi = i
    } catch (e: NumberFormatException) {}
  }
  val x = xv + (4 - xi)
  print(if (x % 15 == 0) "FizzBuzz" else if (x % 3 == 0) "Fizz" else if (x % 5 == 0) "Buzz" else x)
}