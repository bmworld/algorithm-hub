package 백준.Gold.no12852

import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.util.*

private const val IBS = 1 shl 6
private const val OBS = 1 shl 10
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
private const val Q_SEP = MAX * 10
private const val NUM_SEP = 1000
private const val INF = NUM_SEP - 1
fun main() {
  val N = i()
  var opCnt = INF

  val dp = IntArray(MAX + 1) { INF }
  dp[N] = 0 * NUM_SEP + 0

  val q = PriorityQueue<Int>()
  q.add(N)

  op@ while (q.isNotEmpty()) {
    val e = q.poll()
    val cnt = e / Q_SEP
    val fr = e % Q_SEP
    if (fr == 1) {
      if (opCnt > cnt) opCnt = cnt
      continue
    }

    for (num in 3 downTo 2) {
      val to = fr / num
      val nc = cnt + 1 + fr % num
      val pc = dp[to] % NUM_SEP
      if (pc <= nc || to < 1) continue
      dp[to] = fr * NUM_SEP + nc
      q.add(nc * Q_SEP + to)
    }
  }


  w(opCnt, true)

  val out = IntArray(opCnt + 1)
  var oi = opCnt
  var to = 1
  while (oi >= 0 && to != 0) {
    out[oi--] = to
    val e = dp[to]
    val fr = e / NUM_SEP
    if (e != INF && to * 3 != fr && to * 2 != fr) {
      var mid = to * 2
      val mid3 = to * 3
      if (mid3 < fr && mid3 > mid) mid = mid3
      repeat(fr - mid) {
        out[oi--] = mid + it
      }
    }
    to = fr
  }

  repeat(opCnt + 1) {
    w(out[it])
  }

  O.flush()
}
