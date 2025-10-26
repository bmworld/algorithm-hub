package 백준.Bronze.no1546

import java.io.BufferedInputStream

fun main() {
  val n = readInt()
  var max = 0
  var sum = 0.0
  repeat(n) {
    val v = readInt()
    if (max < v) max = v
    sum += v
  }
  val avg: Double = if (max == 0) 0.0 else (sum / max * 100.0) / n
  print(avg)
}

val IN = BufferedInputStream(System.`in`)

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read() // filter
  var n = 0
  while (c in '0'.code..'9'.code) {
    n = n * 10 + (c - '0'.code)
    c = IN.read()
  }
  return n
}
