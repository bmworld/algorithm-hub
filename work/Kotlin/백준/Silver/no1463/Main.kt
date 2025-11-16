package 백준.Silver.no1463

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 11)
private val IN = BufferedInputStream(System.`in`, 1 shl 11)

private fun r(): Int = IN.read()

private fun i(): Int {
  var n = 0
  var c = r()
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = r()
  }
  return n
}

private const val MAX_NUM_LEN = 4
private val outBuf = ByteArray(MAX_NUM_LEN)

private fun writeBy(num: Int) {
  var x = num
  var end = MAX_NUM_LEN - 1
  do {
    outBuf[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = end + 1
  OUT.write(outBuf, stt, MAX_NUM_LEN - stt)
}

fun main() {
  val v = i()
  val dp = IntArray(v + 1)
  for (i in 2..v) {
    var min = dp[i - 1]
    if (i % 3 == 0 && min > dp[i / 3]) min = dp[i / 3]
    if (i % 2 == 0 && min > dp[i / 2]) min = dp[i / 2]
    dp[i] = min + 1
  }
  writeBy(dp[v])
  OUT.flush()
}
