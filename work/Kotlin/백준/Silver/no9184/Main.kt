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
    if (a == -1 && b == -1 && c == -1) break // 마지막입력 = a,b,c 모두 -1

    solveTo(a, b, c, bw)
  }

  bw.flush()
}

/** 제출용 */
fun solveTo(a: Int, b: Int, c: Int, out: Appendable) {

  fun w(a: Int, b: Int, c: Int): Int {
    return if (a <= 0 || b <= 0 || c <= 0) 1
    else if (a > 20 || b > 20 || c > 20) w(20, 20, 20)
    else if (a < b && b < c) w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c)
    else w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1)
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
