package 백준.Gold.no12852

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 3
private const val OBS = 1 shl 7
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
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
private val WB = ByteArray(WS + 1)
private fun w(
  num: Int,
  end: Boolean = false,
) {
  WB[WS] = if (end) 10 else 32
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

private const val MAX = 1_000_000
private const val SEP = 1000
private const val INF = 999 % SEP
fun main() {
  val N = i()
  var opCnt = INF
  val dp = IntArray(MAX + 1) { INF }

  fun op(
    v: Int,
    cnt: Int,
  ) {
    if (cnt >= opCnt) return
    if (v <= 1) {
      opCnt = cnt
      return
    }

    repeat(2) {
      val div = 3 - it
      val nv = v / div
      val nc = cnt + 1 + v % div
      val pc = dp[nv] % SEP
      if (pc > nc) {
        dp[nv] = v * SEP + nc
      }
      op(nv, nc)

    }
  }

  op(N, 0)
  w(opCnt, true)

  var to = 1
  val out = IntArray(opCnt + 1)
  var i = opCnt
  while (i >= 0 && to != INF) {
    out[i--] = to
    val e = dp[to]
    val fr = e / SEP

    if (e != INF && to * 3 != fr && to * 2 != fr) {
      var mid = to * 2
      val mid3 = to * 3
      if (mid3 < fr && mid3 > mid) mid = mid3
      repeat(fr - mid) {
        out[i--] = mid + it
      }
    }
    to = fr
  }

  repeat(opCnt + 1) {
    w(out[it])
  }

  O.flush()
}
