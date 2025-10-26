package 백준.Bronze.no2775

import java.io.BufferedInputStream

fun main() {
  val t = readInt()
  val sb = StringBuilder(t)
  repeat(t) {
    val k = readInt()
    val n = readInt()
    sb.appendLine(APT[k][n])
  }
  print(sb)
}

private const val max = 14
val APT =
    Array(max + 1) { IntArray(max + 1) }
        .also { a ->
          for (n in 1..max) a[0][n] = n

          for (k in 1..max) {
            var sum = 0
            for (n in 1..max) {
              sum += a[k - 1][n]
              a[k][n] = sum
            }
          }
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
