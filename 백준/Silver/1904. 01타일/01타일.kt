import java.io.BufferedInputStream

fun main() {
  val n = readInt(BufferedInputStream(System.`in`))
  println(c[n])
}

val c =
    IntArray(1_000_001).apply {
      this[1] = 1
      this[2] = 2
      for (i in 3..1_000_000) {
        this[i] = (this[i - 1] + this[i - 2]) % 15746
      }
    }

private fun readInt(input: BufferedInputStream): Int {
  var c = input.read()
  while (c <= 32) c = input.read()
  var n = 0
  while (c in 48..57) { // '0' ~ '9'
    n = n * 10 + (c - 48)
    c = input.read()
  }
  return n
}