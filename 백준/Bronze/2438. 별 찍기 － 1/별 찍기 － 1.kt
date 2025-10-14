import java.io.*

fun main() {
  val n = readInt(BufferedInputStream(System.`in`))
  BufferedWriter(OutputStreamWriter(System.out)).use { bw -> solveTo(n, bw) }
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

fun solveTo(n: Int, out: Appendable) {

  val c = CharArray(100) { '*' }
  var i = 1
  while (i <= n) {
    out.append(c.sliceArray(0 until i).concatToString()).append("\n")
    i++
  }
}