package 백준.Bronze.no2869

import java.io.BufferedInputStream

fun main() {
  val up = readInt()
  val dn = readInt()
  val v = readInt()
  if (up >= v) return print(1)

  var d = (v - up) / (up - dn)
  if (d == 0) d = 1 // 최소 1일
  val day = d + 1
  print(day)
}

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
