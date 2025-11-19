package 백준.Silver.no17626

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

fun main() {
  val n = i()

  fun findSqrt(
    v: Int,
    cnt: Int,
    w: Int,
  ) {
    if (cnt > 4 || v < 4 && cnt + v > 4) {
      findSqrt(n, 0, w - 1)
    } else if (v <= 3) {
      w(cnt + v)
      return
    }
    val sq = sqrt(v.toDouble()).toInt() + w
    val next = v - sq * sq //    println("sqrt=${sq}")
    findSqrt(next, cnt + 1, w)
  }


  findSqrt(n, 0, 0)
  OUT.flush()
}
