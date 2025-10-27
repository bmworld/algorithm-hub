import java.io.BufferedInputStream

fun main() {
  val cSort = IntArray(10001) // 1 <= v <= 10,000
  val n = readInt()
  var max = Int.MIN_VALUE
  repeat(n) {
    val v = readInt()
    cSort[v]++
    if (v > max) max = v
  }
  val sb = StringBuilder(n * 2)
  for (v in 1..max) {
    val cnt = cSort[v]
    if (cnt == 0) continue
    repeat(cnt) { sb.appendLine(v) }
  }
  print(sb)
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