import java.io.BufferedOutputStream
import kotlin.math.sqrt

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

private val outBuf = ByteArray(1)

private fun w(num: Int) {
  outBuf[0] = (num + 48).toByte()
  OUT.write(
    outBuf, 0, 1
  )
}

private const val MAX_CNT = 4
fun main() {
  var minCnt = 4
  val n = i()
  var found = false
  val ch = IntArray(MAX_CNT)
  fun findSqrt(
    v: Int,
    sqrt: Int,
    cnt: Int,
  ) {
    val rem = v - sqrt * sqrt
    ch[cnt - 1] = sqrt
    if (found || v <= 0 || cnt >= MAX_CNT || (cnt > 1 && ch[cnt - 2] < ch[cnt - 1])) return
    val sum = cnt + rem
    if (rem <= 3) {
      if (minCnt > sum) minCnt = sum
      if (sum == 1) found = true
      return
    }
    for (s in sqrt(rem.toDouble()).toInt() downTo 2) findSqrt(rem, s, cnt + 1)
  }

  w(
    when {
      n > 3 -> {
        for (s in sqrt(n.toDouble()).toInt() downTo 2) findSqrt(n, s, 1)
        minCnt
      }

      else -> n
    }
  )
  OUT.flush()
}