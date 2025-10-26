package 백준.Bronze.no2609

import java.io.BufferedInputStream

fun main() {
  var a = readInt()
  var b = readInt()
  if (a > b) {
    val tmp = a
    a = b
    b = tmp
  }

  val M = gcd(b, a)
  println(M)

  var j = 1
  var m = a
  if (b % a == 0) {
    m = b
  } else {
    while (m % b != 0) {
      m = a * ++j
    }
  }
  print(m)
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
