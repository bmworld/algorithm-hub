import java.io.*

fun main() {
  val br = BufferedInputStream(System.`in`)
  BufferedWriter(OutputStreamWriter(System.out)).use { bw ->
    val n = readInt(br)
    var i = 1
    while (i < 10) {

      bw.write(n.toString() + " * " + i.toString() + " = " + n * i)
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