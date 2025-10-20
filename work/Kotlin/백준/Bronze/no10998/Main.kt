package 백준.Bronze.no10998

import java.io.BufferedInputStream

fun main() {
  val br = BufferedInputStream(System.`in`)

  print(readInt(br) * readInt(br))
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
