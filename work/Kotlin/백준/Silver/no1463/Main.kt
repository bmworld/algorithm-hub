package 백준.Silver.no1463

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
  out.write(outBuf, stt, MAX_NUM_LEN - stt)
}

fun main() {
  var v = i()
  var min = 100_000
  var cnt = 0
  while (v > 1) {
    when (predictOp(v)) {
      1 -> v /= 3
      2 -> v /= 2
      else -> v--
    }
    cnt++
    if (v == 1) {
      min = cnt
      break
    }
  }
  writeBy(min)
  out.flush()
}

fun predictOp(v: Int): Int {
  return when {
    v <= 3 -> 3
    (v - 1) % 3 == 0 -> 3
    v % 3 == 0 -> 1
    (v - 1) % 2 == 0 -> 3
    v % 2 == 0 -> 2
    else -> 3
  }
}
