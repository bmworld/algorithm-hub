package 백준.Bronze.no2475

import java.io.BufferedInputStream

fun main() {
  val br = BufferedInputStream(System.`in`)
  val sum =
      pow(readInt(br)) + pow(readInt(br)) + pow(readInt(br)) + pow(readInt(br)) + pow(readInt(br))
  println(sum % 10)
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

fun pow(base: Int): Int {
  var result = 1
  repeat(2) { result *= base }
  return result
}
