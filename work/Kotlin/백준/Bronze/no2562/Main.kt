package 백준.Bronze.no2562

import java.io.BufferedInputStream

fun main() {
  val br = BufferedInputStream(System.`in`)
  var max = 0
  var o = 0
  var i = 1
  while (i <= 9) {
    val v = readInt(br)
    if (v > max) {
      max = v
      o = i
    }
    i++
  }
  println(max)
  print(o)
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
