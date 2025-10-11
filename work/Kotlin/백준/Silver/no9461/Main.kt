package 백준.Silver.no9461

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
  val br = BufferedReader(InputStreamReader(System.`in`))
  val t = br.readLine().toInt()
  val sb = StringBuilder(t * 14)
  repeat(t) {
    val n = br.readLine().toInt()
    sb.append(c[n]).append("\n")
  }
  print(sb)
}

val c =
    LongArray(101).also { a ->
      a[1] = 1
      a[2] = 1
      a[3] = 1
      for (i in 4..100) {
        a[i] = a[i - 2] + a[i - 3]
      }
    }

fun solveTo(n: Int, out: Appendable) {
  out.append(c[n].toString()).append("\n")
}

/** 테스트용 */
fun solution(
    n: Int,
): Long = c[n]
