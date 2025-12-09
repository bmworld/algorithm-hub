import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
  val O = BufferedWriter(OutputStreamWriter(System.out))
  val tk = StringTokenizer(readLine())
  val a = tk.nextToken()
    .toBigInteger()
  val b = tk.nextToken()
    .toBigInteger()
  val (q, r) = a.divideAndRemainder(b)
  O.write("$q\n$r")
  O.flush()
}