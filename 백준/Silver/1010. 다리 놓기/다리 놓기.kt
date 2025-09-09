
import java.io.StreamTokenizer

val C = Array(30) { IntArray(30) { 0 } }

fun buildPascal() {
  for (n in 0..29) {
    C[n][0] = 1
    C[n][n] = 1
    for (k in 1 until n) {
      C[n][k] = C[n - 1][k - 1] + C[n - 1][k]
    }
  }
}

fun main() =
    with(StreamTokenizer(System.`in`.bufferedReader())) {
      fun nextInt(): Int {
        nextToken()
        return nval.toInt()
      }

      buildPascal()

      val sb = StringBuilder()
      repeat(nextInt()) {
        val m = nextInt() // 서
        val n = nextInt() // 동
        sb.append(C[n][m]).append('\n') // nCm
      }

      print(sb)
    }
