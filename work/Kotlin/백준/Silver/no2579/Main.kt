package 백준.Silver.no2579

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
  val end = i()
  val a = IntArray(end)
  repeat(end) { a[it] = i() }
  val dp = IntArray(end)

  fun op(
      acc: Int,
      i: Int,
      step: Int,
  ) {
    if (i >= end) return
    val next = acc + a[i]
    if (dp[i] < next) dp[i] = next else return

    op(next, i + 2, 2)
    if (step % 2 == 0) op(next, i + 1, 1)
  }
  op(0, 0, 0)

  w(dp[end - 1])
  OUT.flush()
}
