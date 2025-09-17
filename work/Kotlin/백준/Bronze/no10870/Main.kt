package 백준.Bronze.no10870

import java.io.BufferedInputStream

fun main() {
  println(fib(readInt(BufferedInputStream(System.`in`))))
}

private fun fib(n: Int): Int {
  if (n <= 1) return n
  var a = 0
  var b = 1
  var i = 2
  while (i <= n) {
    val t = a + b
    a = b
    b = t
    i++
  }
  return b
}

private fun readInt(input: BufferedInputStream): Int {
  var c = input.read()
  while (c <= 32) c = input.read()
  var n = 0
  while (c in 48..57) { // '0' ~ '9'
    n = n * 10 + (c - 48)
    c = input.read()
  }
  return n
}

/** 테스트용 */
fun solution(n: Int): Int {
  return fib(n)
}
