import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 20)

private val IN: ByteArray = System.`in`.readBytes()
private var inPos = 0
private const val EOF = -1

private fun r(): Int = if (inPos < IN.size) (IN[inPos++].toInt() and 0xFF) else EOF

private fun i(): Int {
  var n = 0
  var c = r()
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = r()
  }
  return n
}

private const val MAX_NUM_LEN = 10
private val outBuf = ByteArray(MAX_NUM_LEN + 1).also { it[MAX_NUM_LEN] = '\n'.code.toByte() }

private fun w(num: Int) {
  var n = num
  var end = MAX_NUM_LEN - 1
  do {
    outBuf[end--] = ((n % 10) + 48).toByte()
    n /= 10
  } while (n > 0)
  val stt = end + 1
  OUT.write(
    outBuf, stt, MAX_NUM_LEN - stt + 1 // 개행 포함
  )
}

fun main() {
  val N = i()
  val M = i()
  val acc = IntArray(N + 1)
  repeat(N) {
    val v = i()
    acc[it + 1] = acc[it] + v
  }
  repeat(M) {
    val i = i()
    val j = i()
    w(acc[j] - acc[i - 1])
  }
  OUT.flush()
}