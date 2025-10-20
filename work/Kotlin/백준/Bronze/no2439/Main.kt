package 백준.Bronze.no2439

import java.io.BufferedInputStream

fun main() {
  val n = readInt(BufferedInputStream(System.`in`))
  val sb = StringBuilder()
  for (i in 1..n) {
    repeat(n - i) { sb.append(' ') }
    repeat(i) { sb.append('*') }
    sb.append('\n')
  }

  print(sb)
}

private fun readInt(input: BufferedInputStream): Int {
  var c = input.read()
  while (c <= 32) c = input.read()
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = input.read()
  }
  return n
}
