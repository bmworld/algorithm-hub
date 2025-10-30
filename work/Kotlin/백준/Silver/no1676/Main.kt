package 백준.Silver.no1676

import java.io.BufferedInputStream

fun main() {
  val n = readInt()
  print((n / 5) + (n / 25) + (n / 125))
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
