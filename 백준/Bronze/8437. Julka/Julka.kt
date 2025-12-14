import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
  val O = BufferedWriter(OutputStreamWriter(System.out))
  val all = readLine().toBigInteger()
  val diff = readLine().toBigInteger()

  val b = all.minus(diff)
    .divide(2.toBigInteger())
  val a = b.plus(diff)
  O.write("$a\n")
  O.write("${b}")
  O.flush()
}