import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() {
  val br = System.`in`.bufferedReader()
  val n = br.readLine().toInt()
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
  val fibStr = Array(21) { fib[it].toString() }

  val answer = fibStr[n]
  when (out) {
    is BufferedWriter -> out.write(answer)
    else -> out.append(answer)
  }
}