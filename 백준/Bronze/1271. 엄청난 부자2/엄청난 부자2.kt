import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
  val tk = StringTokenizer(readLine())
  val a = tk.nextToken()
    .toBigInteger()
  val b = tk.nextToken()
    .toBigInteger()
  val (q, r) = a.divideAndRemainder(b)
  println(q)
  print(r)
}