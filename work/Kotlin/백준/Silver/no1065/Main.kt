package 백준.Silver.no1065

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 3
private const val OBS = 1 shl 3
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = BufferedInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0
private const val EOF = -1

private fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

private val NUM = 48..57
private fun i(): Int {
  var v = 0
  var s = 1
  var c: Byte
  while (r().also { c = it } in NUM || c == 45.toByte()) {
    when (c) {
      in NUM -> v = v * 10 + c - 48
      else -> s = -1
    }
  }
  return s * v
}

private const val WS = 10
private val WB = ByteArray(WS)
private fun w(
  num: Int,
  flush: Boolean = true,
) {
  var v = if (num >= 0) num
  else {
    O.write(45)
    -num
  }
  var pos = WS - 1
  do {
    WB[pos--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  O.write(WB, ++pos, WS - pos)
  if (flush) O.flush()
}

private const val MAX_NUM = 999
private const val MAX_LEN = 4
private const val CNT_UNTIL_2ND_PLACE = 99
fun main() {
  val N = minOf(i(), MAX_NUM)
  if (N <= CNT_UNTIL_2ND_PLACE) {
    w(N)
    return
  }

  val NUM = IntArray(MAX_LEN).also {
    var x = N
    var i = 0
    while (x > 0) {
      it[i++] = x % 10
      x /= 10
    }
  }

  var cnt = CNT_UNTIL_2ND_PLACE
  val p3 = NUM[2]
  val p2 = NUM[1]
  val p1 = NUM[0]
  loop@ for (d3 in 1..p3) {
    for (d2 in 0..9) {
      val diff1 = d3 - d2
      if (d3 >= p3 && d2 > p2) break@loop
      for (d1 in 0..9) {
        val diff2 = d2 - d1
        if (diff1 == diff2) {
          cnt++
          break
        } else if (d3 == p3 && d2 == p2 && d1 == p1) break@loop
      }
    }
  }

  w(cnt)
}
