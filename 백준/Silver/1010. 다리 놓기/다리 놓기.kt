
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

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

fun main() {

  val br = BufferedReader(InputStreamReader(System.`in`))
  val t = br.readLine().trim().toInt()
  buildPascal()

  val sb = StringBuilder()
  repeat(t) {
    val st = StringTokenizer(br.readLine(), " ")
    val m = st.nextToken().toInt() // 서
    val n = st.nextToken().toInt() // 동
    sb.append(C[n][m]).append('\n') // nCm
  }

  print(sb)
}
