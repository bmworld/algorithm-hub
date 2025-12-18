import java.io.BufferedOutputStream

private val out = BufferedOutputStream(System.`out`, 1 shl 11)

private val iBytes: ByteArray = System.`in`.readBytes()
private var iPos = 0
private const val EOF = -1

private fun r(): Int = if (iPos < iBytes.size) (iBytes[iPos++].toInt() and 0xFF) else EOF

private fun i(): Int {
  var n = 0
  var c = r()
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = r()
  }
  return n
}

private const val MAX_NUM_LEN = 9
private val outBuf = ByteArray(MAX_NUM_LEN + 1)

private fun writeBy(num: Int, isEnd: Boolean) {
  var x = num
  outBuf[MAX_NUM_LEN] = if (isEnd) '\n'.code.toByte() else ' '.code.toByte()
  var end = MAX_NUM_LEN - 1
  do {
    outBuf[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = end + 1

  out.write(
      outBuf,
      stt,
      MAX_NUM_LEN - stt + 1, // + 띄어쓰기
  )
}

private fun writeCnt(zero: Int, one: Int) {
  writeBy(zero, false)
  writeBy(one, true)
}

private val CNT =
    IntArray(41).also {
      it[0] = 1
      it[1] = 1
      for (i in 2..40) {
        it[i] = it[i - 1] + it[i - 2]
      }
    }

fun main() {
  repeat(i()) {
    val v = i()
    if (v > 1) writeCnt(CNT[v - 2], CNT[v - 1])
    else
        when (v) {
          0 -> writeCnt(1, 0)
          1 -> writeCnt(0, 1)
        }
  }
  out.flush()
}