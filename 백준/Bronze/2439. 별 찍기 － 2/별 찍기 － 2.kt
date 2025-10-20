import java.io.BufferedInputStream

fun main() {
  val n = readInt(BufferedInputStream(System.`in`))
  val sb = StringBuilder()
  val line = Array(n) { " " }
  var i = 1
  while (i <= n) {
    line[n - i] = "*"
    sb.append(line.joinToString("")).append('\n')
    i++
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