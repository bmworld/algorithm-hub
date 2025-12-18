package 백준.Bronze.no2292

import java.io.BufferedInputStream

fun main() {
  val n = readInt()
  var layer = 1
  var v = 1
  while (v <= 1_000_000_000) {
    if (n <= v) break
    v += 6 * layer
    layer++
  }
  print(layer)
}

val IN = BufferedInputStream(System.`in`)

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = IN.read()
  }
  return n
}
