import java.io.*

fun main() =
    with(StreamTokenizer(System.`in`.bufferedReader())) {
      fun nextInt(): Int {
        nextToken()
        return nval.toInt()
      }

      BufferedWriter(OutputStreamWriter(System.out)).use { bw ->
        solveTo(nextInt(), bw) // BufferedWriter를 그대로 넘김
        bw.flush()
      }
    }

/** 제출용 */
fun solveTo(n: Int, out: Appendable) {

  val answer: Int = FIBO[n]

  when (out) {
    is BufferedWriter -> {
      out.write(answer.toString())
      out.newLine()
    }

    else -> out.append(answer.toString())
  }
}

private val FIBO =
    IntArray(21).apply {
      this[0] = 0
      this[1] = 1
      for (i in 2..20) this[i] = this[i - 1] + this[i - 2]
    }