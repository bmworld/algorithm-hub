package 백준.Bronze.no3052

import java.io.BufferedInputStream

val IN = BufferedInputStream(System.`in`)

fun main() {
  var cnt = 0
  val used = BooleanArray(42)
  repeat(10) {
    val r = readInt() % 42
    if (!used[r]) {
      cnt++
      used[r] = true
    }
  }
  print(cnt)
}

fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var n = 0
  while (c >= 48 && c <= 57) {
    n = n * 10 + (c - '0'.code)
    c = IN.read()
  }
  return n
}
