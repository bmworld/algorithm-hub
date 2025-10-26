import java.io.BufferedInputStream

fun main() {
  val n = readInt()
  var max = 0
  var sum = 0.0
  repeat(n) {
    val v = readInt()
    if (max < v) max = v
    sum += v
  }

  val fake: Double = sum / max * 100 / n
  print(fake)
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