package 백준.Bronze.no2675

import java.io.BufferedInputStream

fun main() {
  val br = BufferedInputStream(System.`in`)
  val n = readInt(br)
  repeat(n) {
    val sb = StringBuilder()
    val cnt = readInt(br)
    val s = readString(br)
    for (c in s) {
      repeat(cnt) { sb.append(c) }
    }
    println(sb)
  }
}

fun readInt(input: BufferedInputStream): Int {
  var c = input.read()
  while (c <= 32) c = input.read()
  var sign = 1
  if (c == '-'.code) { // 부호 처리
    sign = -1
    c = input.read()
  }
  var n = 0
  while (c in '0'.code..'9'.code) {
    n = n * 10 + (c - '0'.code)
    c = input.read()
  }
  return n * sign
}

fun readString(input: BufferedInputStream): String {
  val sb = StringBuilder()
  var c = input.read()
  val isEnd = c != -1
  while (c <= 32 && isEnd) c = input.read()
  while (c > 32 && isEnd) {
    sb.append(c.toChar())
    c = input.read()
  }

  return sb.toString()
}
