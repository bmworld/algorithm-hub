package 백준.Silver.no5623

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 3_000
private const val OBS = 1 shl 10
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

fun main() {
  val N = i()
  val a = IntArray(N) { 1 }
  val SUM = IntArray(N * N)

  repeat(N) { i ->
    repeat(N) { j ->
      val rPlusC = i()
      SUM[encodePos(i, j, N)] = rPlusC
      if (i < 1 || j < 2) return@repeat
      val rMinusC = SUM[encodePos(i - 1, j, N)] - SUM[encodePos(i - 1, j + 1, N)]
      a[i] = (rPlusC - rMinusC) / 2
    }
  }
  a[0] = SUM[encodePos(0, 1, N)] - a[1]
  val last = N - 1
  a[last] = SUM[encodePos(last - 1, last, N)] - a[last - 1]
  repeat(N) {
    w(a[it])
  }
  O.flush()
}

fun encodePos(
  r: Int,
  c: Int,
  CAP: Int
): Int = r * CAP + c
