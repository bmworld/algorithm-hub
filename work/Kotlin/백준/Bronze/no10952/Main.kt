package 백준.Bronze.no10952

import java.util.*

fun main() {
  val br = System.`in`.bufferedReader()
  val sb = StringBuilder()
  while (true) {
    val l = br.readLine() ?: break

    val t = StringTokenizer(l)
    val a = t.nextToken().toInt()
    val b = t.nextToken().toInt()

    if (a == 0 && b == 0) break
    sb.append(a + b).append('\n')
  }
  print(sb)
}
