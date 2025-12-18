import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private val IN = BufferedInputStream(System.`in`, 1 shl 19)
private val OUT = BufferedOutputStream(System.`out`, 1 shl 18)
private val buf = ByteArray(10)

private fun readByte(): Int = IN.read()

fun main() {
  val n = readInt()
  val arr = IntArray(n)
  var len = 0
  var sum = 0
  repeat(n) {
    val v = readInt()
    if (v == 0) {
      sum -= arr[--len]
    } else {
      arr[len++] = v
      sum += v
    }
  }
  writeln(sum)
  OUT.flush()
}

private fun writeln(num: Int) {
  var x = num
  var endIdx = buf.size - 1
  do {
    buf[endIdx--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = endIdx + 1
  val len = buf.size - stt
  OUT.write(buf, stt, len)
}

private fun readInt(): Int {
  var n = 0
  var sign = 1
  var c = readByte()
  while (c !in 48..57) c = readByte()
  while (true) {
    when (c) {
      in 48..57 -> {
        n = n * 10 + (c - 48)
        c = IN.read()
      }
      10,
      13,
      32 -> return n * sign
      45 -> {
        sign = -1
        c = readByte()
      }
      else -> c = readByte()
    }
  }
}