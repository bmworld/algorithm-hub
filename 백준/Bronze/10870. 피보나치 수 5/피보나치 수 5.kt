import java.io.*

fun main() =
    with(BufferedReader(InputStreamReader(System.`in`))) {
      val n = readLine().toInt()
      val bw = BufferedWriter(OutputStreamWriter(System.out))
      solveTo(n, bw)
      bw.flush()
    }

/** 제출용 */
fun solveTo(n: Int, out: Appendable) {

  val fib =
      IntArray(21).apply {
        this[0] = 0
        this[1] = 1
        for (i in 2..20) this[i] = this[i - 1] + this[i - 2]
      }

  val answer = fib[n]
  when (out) {
    is BufferedWriter -> println(answer)
    else -> out.append(answer.toString())
  }
}