import java.io.*

fun main() {
  val br = BufferedInputStream(System.`in`)
  val n = readInt(br)
  BufferedWriter(OutputStreamWriter(System.out)).use { bw ->
    var i = 1
    while (i <= n) {
      bw.write(i.toString())
      bw.write('\n'.code)
      i++
    }
  }
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