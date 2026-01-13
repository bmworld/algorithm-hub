package 백준.Silver.no14753

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 16
private const val OBS = 1 shl 8
private val O = BufferedOutputStream(System.out, OBS)
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
}

private const val HALF = 1_000
private const val NEG_LIMIT = 2
private const val POS_LIMIT = 3

fun main() {

  val N = i()
  val a = IntArray(HALF * 2 + 1)
  var MIN = Int.MAX_VALUE
  var maxOfPos = 1
  var negCnt = 0
  var posCnt = 0
  repeat(N) {
    val v = i()
    a[v + HALF]++
    if (v < 0) negCnt++
    if (v > 0) posCnt++
    if (v > 0 && v > maxOfPos) maxOfPos = v
    if (MIN > v) MIN = v
  }

  if (negCnt > NEG_LIMIT) negCnt = NEG_LIMIT
  if (posCnt > POS_LIMIT) posCnt = POS_LIMIT


  w(
    when {
      negCnt >= NEG_LIMIT -> {

        var nMax = maxOfPos
        for (v in MIN..-1) {
          var cnt = a[v + HALF]
          while (cnt > 0 && negCnt > 0) {
            nMax *= v
            cnt--
            negCnt--
          }
        }

        val pmax = getMaxOfPos(a, maxOfPos, posCnt)
        if (nMax > pmax) nMax else pmax
      }

      posCnt >= 2 -> getMaxOfPos(a, maxOfPos, posCnt)

      else -> 0
    }
  )
  O.flush()
}

private fun getMaxOfPos(
  a: IntArray,
  max: Int,
  limit: Int,
): Int {
  var balance = limit
  var posV = 1
  for (v in max downTo 1) {
    var cnt = a[v + HALF]
    while (cnt > 0 && balance > 0) {
      posV *= v
      cnt--
      balance--
    }
  }
  return posV
}
