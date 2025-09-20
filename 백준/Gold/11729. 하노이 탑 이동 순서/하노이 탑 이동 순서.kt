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
  var cnt = 0
  val str = StringBuilder()

  fun move(l: Int, from: Int, to: Int, via: Int) {
    if (l == 1) {
      cnt++
      str.append("$from $to").append("\n")
    } else {
      move(l - 1, from, via, to)
      cnt++
      str.append("$from $to").append("\n")
      move(l - 1, via, to, from)
    }
  }

  move(n, 1, 3, 2)

  out.append(cnt.toString()).append('\n')
  out.append(str)
}