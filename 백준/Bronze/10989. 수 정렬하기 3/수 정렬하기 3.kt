import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private val IN = BufferedInputStream(System.`in`, 1 shl 20)
private val OUT = BufferedOutputStream(System.`out`, 1 shl 20)

fun main() {
  val size = 10001
  val arr = IntArray(size)
  val n = readInt()
  var max = 0

  repeat(n) {
    val v = readInt()
    arr[v]++
    if (v > max) max = v
  }

  val buf = ByteArray(6) // 최대 "10000\n"
  for (v in 1..max) {
    var k = arr[v]
    if (k == 0) continue

    var x = v
    var end = 5
    buf[end--] = '\n'.code.toByte()
    do {
      buf[end--] = ((x % 10) + 48).toByte()
      x /= 10
    } while (x > 0)

    val stt = end + 1
    val len = 6 - stt
    while (k-- > 0) OUT.write(buf, stt, len)
  }

  OUT.flush()
}

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read() // filter
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - '0'.code)
    c = IN.read()
  }
  return n
}