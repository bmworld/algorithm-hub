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

private const val MAX_NUM_LEN = 20
private val outBuf = ByteArray(MAX_NUM_LEN)

private fun w(
  num: Long,
) {
  var x = num
  var end = MAX_NUM_LEN - 1
  do {
    outBuf[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = end + 1

  OUT.write(
    outBuf, stt, MAX_NUM_LEN - stt
  )
}



fun main() {
  val k = i()
  val n = i()
  val a = LongArray(k)
  var minLen: Long = Long.MAX_VALUE
  var maxLen = 0L
  repeat(k) {
    val v = i().toLong()
    a[it] = v
    if (v < minLen) minLen = v
    if (v > maxLen) maxLen = v
  }


  fun pSearch(
    min: Long,
    max: Long,
  ): Long {
    var maxLen = min
    var l = min
    var r = max
    while (l <= r) {
      val m = (l + r) / 2
      var cnt = 0L
      for (v in a) cnt += v / m
      when {
        cnt >= n -> {
          if (m > maxLen) maxLen = m
          l = m + 1
        }

        else -> r = m - 1
      }
    }
    return maxLen
  }

  w(pSearch(1, maxLen))
  OUT.flush()
}