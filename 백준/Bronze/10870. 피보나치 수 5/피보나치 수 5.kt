import java.io.BufferedInputStream

fun main() {
  val br = BufferedInputStream(System.`in`)
  val n = readInt(br)
  print(fib[n])
}

private fun readInt(input: BufferedInputStream): Int {
  var c = input.read()
  while (c <= 32) c = input.read()
  var n = 0
  while (c >= '0'.code) {
    n = n * 10 + (c - '0'.code)
    c = input.read()
  }
  return n
}

val fib =
    IntArray(21).apply {
      this[0] = 0
      this[1] = 1
      for (i in 2..20) this[i] = this[i - 1] + this[i - 2]
    }