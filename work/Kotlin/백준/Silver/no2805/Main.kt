package 백준.Silver.no2805

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

private const val MAX_NUM_LEN = 10
private val outBuf = ByteArray(MAX_NUM_LEN)

private fun w(
  num: Int,
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
  val n = i()
  val goal = i()
  val a = IntArray(n)
  var r = 0
  var l = 1_000_000_000
  repeat(n) {
    val v = i()
    a[it] = v
    if (v < l) l = v
    if (v > r) r = v
  }

  var maxH = 0
  while (l <= r) {
    var sum = 0
    val m = (l + r) / 2
    for (v in a) {
      if (v <= m) continue
      sum += v - m
    }
    when {
      sum >= goal -> {
        maxH = m
        l = m + 1
      }

      else -> r = m - 1
    }
  }

  w(maxH)
  OUT.flush()
}
