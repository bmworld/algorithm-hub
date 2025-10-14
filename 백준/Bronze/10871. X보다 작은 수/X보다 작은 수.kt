import java.io.BufferedInputStream

fun main() {
  val br = BufferedInputStream(System.`in`)
  val n = readInt(br)
  val x = readInt(br)
  val sb = StringBuilder(n * 6)
  var first = true
  repeat(n) {
    val v = readInt(br)

    if (v < x) {
      if (!first) sb.append(' ')
      sb.append(v)
      first = false
    }
  }

  print(sb)
}

private fun readInt(input: BufferedInputStream): Int {
  var c = input.read()
  while (c <= 32) c = input.read()
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = input.read()
  }
  return n
}