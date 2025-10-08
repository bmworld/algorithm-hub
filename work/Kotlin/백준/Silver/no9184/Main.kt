package 백준.Silver.no9184

import java.io.*

fun main() {
  val br = BufferedReader(InputStreamReader(System.`in`))
  val bw = BufferedWriter(OutputStreamWriter(System.out))

  while (true) {
    val line = br.readLine() ?: break

    val str = line.split(" ")
    val a = str[0].toInt()
    val b = str[1].toInt()
    val c = str[2].toInt()
    if (a == -1 && b == -1 && c == -1) break

    solveTo(a, b, c, bw)
  }

  bw.flush()
}

val cache = Array(101) { Array(101) { IntArray(101) } }

/** 제출용 */
fun solveTo(a: Int, b: Int, c: Int, out: Appendable) {

  fun w(a: Int, b: Int, c: Int): Int {
    return if (a <= 0 || b <= 0 || c <= 0) 1
    else if (cache[a][b][c] != 0) cache[a][b][c]
    else if (a > 20 || b > 20 || c > 20) {
      val v = w(20, 20, 20)
      cache[20][20][20] = v
      v
    } else if (a < b && b < c) {
      val v1 = w(a, b, c - 1)
      cache[a][b][c - 1] = v1
      val v2 = w(a, b - 1, c - 1)
      cache[a][b - 1][c - 1] = v2
      val v3 = w(a, b - 1, c)
      cache[a][b - 1][c] = v3
      v1 + v2 - v3
    } else {
      val v1 = w(a - 1, b, c)
      cache[a - 1][b][c] = v1
      val v2 = w(a - 1, b - 1, c)
      cache[a - 1][b - 1][c] = v2
      val v3 = w(a - 1, b, c - 1)
      cache[a - 1][b][c - 1] = v3
      val v4 = w(a - 1, b - 1, c - 1)
      cache[a - 1][b - 1][c - 1] = v4
      v1 + v2 + v3 - v4
    }
  }

  val result = w(a, b, c)

  out.append("w(" + a + ", " + b + ", " + c + ") = " + result).append("\n")
}

/** 테스트용 */
fun solution(
    a: Int,
    b: Int,
    c: Int,
): String {
  val sb = StringBuilder()
  solveTo(a, b, c, sb)
  return sb.trimEnd().toString()
}
