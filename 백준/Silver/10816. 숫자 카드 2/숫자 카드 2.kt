import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private val IN = BufferedInputStream(System.`in`, 1 shl 20)
private val OUT = BufferedOutputStream(System.`out`, 1 shl 21)
private const val MAX_NUM_LEN = 6
private val buf = ByteArray(MAX_NUM_LEN + 1).also { it[MAX_NUM_LEN] = ' '.code.toByte() }
private const val HALF = 10_000_000

private fun readByte(): Int = IN.read()

fun main() {
  val n = readInt()
  val arr = IntArray(HALF * 2 + 1)
  repeat(n) {
    val v = readInt()
    arr[v + HALF]++
  }
  val m = readInt()
  repeat(m) {
    val v = readInt()
    writeln(arr[v + HALF])
  }
  OUT.flush()
}

private fun writeln(num: Int) {
  var x = num
  var endIdx = MAX_NUM_LEN - 1
  do {
    buf[endIdx--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = endIdx + 1
  val len = MAX_NUM_LEN - stt + 1 // 공백 포함
  OUT.write(buf, stt, len)
}

private fun readInt(): Int {
  var n = 0
  var sign = 1
  var c = readByte()
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