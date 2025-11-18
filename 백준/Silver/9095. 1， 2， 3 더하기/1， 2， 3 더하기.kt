import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 11)

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

private const val MAX_NUM_LEN = 3
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
  repeat(i()) {
    w(CNT[i() - 1])
  }
  OUT.flush()
}



private val CNT = IntArray(11).also {
  it[0] = 1
  it[1] = 2
  it[2] = 4
  it[3] = 7
  it[4] = 13
  it[5] = 24
  it[6] = 44
  it[7] = 81
  it[8] = 149
  it[9] = 274
  it[10] = 504
}