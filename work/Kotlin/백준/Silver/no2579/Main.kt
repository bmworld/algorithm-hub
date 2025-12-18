package 백준.Silver.no2579

import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 12)

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

private const val MAX_NUM_LEN = 7
private val outBuf = ByteArray(MAX_NUM_LEN)

private fun w(num: Int) {
  var n = num
  var end = MAX_NUM_LEN - 1
  do {
    outBuf[end--] = ((n % 10) + 48).toByte()
    n /= 10
  } while (n > 0)
  val stt = end + 1
  OUT.write(outBuf, stt, MAX_NUM_LEN - stt)
}

fun main() {
  val cnt = i()
  val a = IntArray(cnt)
  repeat(cnt) { a[it] = i() }
  val dp = IntArray(cnt)
  dp[0] = a[0]
  if (cnt >= 2) dp[1] = a[0] + a[1]
  if (cnt >= 3) dp[2] = if (a[0] + a[2] > a[1] + a[2]) a[0] + a[2] else a[1] + a[2]

  for (i in 3 until cnt) {
    val case1 = dp[i - 3] + a[i - 1]
    val case2 = dp[i - 2]
    dp[i] = (if (case1 > case2) case1 else case2) + a[i]
  }
  w(dp[cnt - 1])
  OUT.flush()
}
