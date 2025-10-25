package 백준.Bronze.no15829

import java.io.BufferedInputStream

fun main() {
  val L = readInt()
  val r = 31L
  val M = 1234567891L
  var sum = 0L
  var power = 1L // 지수
  repeat(L) {
    val v = IN.read() - 96 // a=1 ... z=26
    sum = (v * power + sum) % M
    power = (r * power) % M
  }

  print(sum.toInt())
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
