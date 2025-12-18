package 백준.Bronze.no1271

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
  val O = BufferedWriter(OutputStreamWriter(System.out))
  val tk = StringTokenizer(readLine())
  val (q, r) = tk.nextToken()
    .toBigInteger()
    .divideAndRemainder(
      tk.nextToken()
        .toBigInteger()
    )
  O.write("$q")
  O.write("\n")
  O.write("$r")
  O.flush()
}
