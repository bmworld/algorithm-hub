package 백준.Bronze.no2920

import java.io.BufferedInputStream

fun main() {
  val IN = BufferedInputStream(System.`in`)
  var M = 8
  var m = 1
  repeat(8) {
    var c = IN.read()
    while (c <= 32) c = IN.read()
    val n = c - 48
    if (n == M) M-- else if (n == m) m++
  }
  print(if (M == 0) "descending" else if (m == 8) "ascending" else "mixed")
}
