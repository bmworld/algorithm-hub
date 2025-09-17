package 백준.Bronze.no10870

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() =
    with(BufferedReader(InputStreamReader(System.`in`))) {
      val n = readLine().toInt()
      solveTo(n, null)
    }

/** 제출용 */
fun solveTo(n: Int, out: Appendable?) {

  var a = 0
  var b = 1
  for (i in 2..n) {
    val tmp = a + b
    a = b
    b = tmp
  }
  val answer = if (n == 0) 0 else if (n == 1) 1 else b

  when (out) {
    is StringBuilder -> out.append(answer.toString())
    else -> println(answer)
  }
}

/** 테스트용 */
fun solution(n: Int): Int {
  val sb = StringBuilder()
  solveTo(n, sb)
  return sb.toString().toInt()
}
