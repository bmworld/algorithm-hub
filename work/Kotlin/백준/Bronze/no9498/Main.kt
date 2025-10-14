package 백준.Bronze.no9498

import java.io.BufferedInputStream

fun main() {
  val br = BufferedInputStream(System.`in`)
  val n = readInt(br)
  when {
    n >= 90 && n <= 100 -> print('A')
    n >= 80 && n < 90 -> print('B')
    n >= 70 && n < 80 -> print('C')
    n >= 60 && n < 70 -> print('D')
    else -> print('F')
  }
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
