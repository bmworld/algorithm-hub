package 백준.Bronze.no31430

import java.io.BufferedInputStream

fun main() {
  val br = BufferedInputStream(System.`in`)
  val a = readInt(br)
  val b = readInt(br)
  val c = readInt(br)
  println(a + b - c)
  print((a.toString() + b.toString()).toInt() - c)
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
