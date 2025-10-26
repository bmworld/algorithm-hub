package 백준.Bronze.no2609

import java.io.BufferedInputStream

fun main() {
  val a = readInt()
  val b = readInt()
  val G = gcd(b, a)
  println(G)
  val L = a * b / G
  print(L)
}

fun gcd(a: Int, b: Int): Int = if (b != 0) gcd(b, a % b) else a

val IN = BufferedInputStream(System.`in`)

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read() // filter
  var n = 0
  while (c in '0'.code..'9'.code) {
    n = n * 10 + (c - '0'.code)
    c = IN.read()
  }
  return n
}
