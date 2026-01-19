package 백준.Gold.no25908

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 5
private const val OBS = 1 shl 5
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

private val WB = ByteArray(WS + 1).also { it[WS] = 32 }
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
  O.write(WB, ++pos, WS - pos + 1)
}

private const val EMPTY = Int.MIN_VALUE
private const val EVEN_W = 1
private const val ODD_W = -1

private const val D2 = 2
private const val D3 = 3
private const val D5 = 5
private const val D7 = 7
private val DVSRS = intArrayOf(D2, D3, D5, D7)

fun main() {
  val stt = i()
  val end = i()
  val a = IntArray(end + 1) { EMPTY }
  a[1] = ODD_W

  var total = if (stt <= 1) a[1] else 0

  repeat(DVSRS.size) {
    total += fillByPow(a, stt, end, DVSRS[it])
  }

  repeat(end - stt + 1) { i ->
    val num = stt + i
    if (a[num] != EMPTY) return@repeat

    total += if (isDividable(num)) {
      val w = getW(num)
      a[num] = w
      w
    } else fillByPow(a, stt, end, num)
  }

  w(total)
  O.flush()
}

private fun fillByPow(
  a: IntArray,
  min: Int,
  max: Int,
  dvsr: Int
): Int {
  var acc = 0
  var num = dvsr
  val w = getOneW(dvsr)
  var nw = w
  while (num in 1..max) {
    if (num >= min && a[num] == EMPTY) a[num] = (ODD_W + nw).also { acc += it }
    nw += w
    num *= dvsr
  }

  return acc
}

private fun getOneW(dvsr: Int) = if (dvsr % 2 == 0) EVEN_W else ODD_W

fun isDividable(v: Int): Boolean =
  v % D2 == 0 ||
    v % D3 == 0 ||
    v % D5 == 0 ||
    v % D7 == 0

private val DvsrCnts = IntArray(DVSRS.size)
private fun getW(num: Int): Int {
  if (num == 1) return ODD_W
  var x = num
  repeat(DVSRS.size) {
    val d = DVSRS[it]
    var cnt = 1
    while (x % d == 0) {
      x /= d
      cnt++
    }
    DvsrCnts[it] = cnt
  }

  val oCnt = DvsrCnts[1] * DvsrCnts[2] * DvsrCnts[3]
  val eCnt = (DvsrCnts[0] - 1) * oCnt
  return oCnt * ODD_W + eCnt * EVEN_W
}
