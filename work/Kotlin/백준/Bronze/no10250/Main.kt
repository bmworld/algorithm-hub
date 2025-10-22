package 백준.Bronze.no10250

import java.io.BufferedInputStream

fun main() {
  val t = readInt()
  repeat(t) {
    val h = readInt()
    val w = readInt()
    val n = readInt()

    val room = (n - 1) / h + 1
    var floor = n % h
    if (floor == 0) floor = h
    println(floor * 100 + room)
  }
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
