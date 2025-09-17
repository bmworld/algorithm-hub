package 백준.Bronze.no10870

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() =
    with(BufferedReader(InputStreamReader(System.`in`))) {
      val n = readLine().toInt()
      print(fib[n])
    }

val fib =
    IntArray(21).apply {
      this[0] = 0
      this[1] = 1
      for (i in 2..20) this[i] = this[i - 1] + this[i - 2]
    }

/** 테스트용 */
fun solution(n: Int): Int {
  return fib[n]
}
