package 백준.Bronze.no2739

import java.io.*

fun main() {
  val br = BufferedInputStream(System.`in`)
  val n = readInt(br)
  val sb = StringBuilder(64)
  var i = 1
  while (i <= 9) {
    sb.append(n).append(" * ").append(i).append(" = ").append(n * i).append('\n')
    i++
  }

  BufferedWriter(OutputStreamWriter(System.out)).use { bw -> bw.write(sb.toString()) }
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
