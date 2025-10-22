package 백준.Bronze.no2577

import java.io.*

val IN = BufferedInputStream(System.`in`)
val OUT = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
  var n = readInt() * readInt() * readInt()
  val cnts = IntArray(10)
  while (n > 0) {
    cnts[n % 10]++
    n /= 10
  }

  for (i in 0..9) {
    OUT.write(48 + cnts[i]) // 숫자
    OUT.write(10) // 개행
  }

  OUT.flush()
}

fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var n = 0
  while (c in '0'.code..'9'.code) {
    n = n * 10 + (c - '0'.code)
    c = IN.read()
  }
  return n
}
