package 백준.Gold.no7569

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 11_000
private const val OBS = 4
private const val EOF = -1
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0

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
  var v = num
  if (v < 0) {
    v = -v
    O.write(45)
  }
  var pos = WS - 1
  do {
    WB[pos--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  O.write(WB, pos + 1, -(pos + 1) + WS)
}

private const val RIPE = 1
private const val UNRIPE = 0
private const val H_SEP = 10000
private const val R_SEP = 100
private val dh = intArrayOf(1, -1, 0, 0, 0, 0)
private val dr = intArrayOf(0, 0, 1, 0, -1, 0)
private val dc = intArrayOf(0, 0, 0, 1, 0, -1)
fun main() {
  val rs = i()
  val cs = i()
  val hs = i()
  val box = Array(hs) { Array(rs) { IntArray(cs) } }
  val q = IntArray(hs * rs * cs)
  var qh = 0
  var qt = 0
  var unripeCnt = 0
  repeat(hs) { h ->
    repeat(cs) { c ->
      repeat(rs) { r ->
        val v = i()
        box[h][r][c] = v
        if (v == UNRIPE) unripeCnt++
        if (v == RIPE) q[qt++] = h * H_SEP + r * R_SEP + c
      }
    }
  }

  if (unripeCnt == 0) {
    w(0)
    O.flush()
    return
  }

  var totalDays = 0
  while (qh < qt) {
    val hrc = q[qh++]
    val h = hrc / H_SEP
    val rc = hrc % H_SEP
    val r = rc / R_SEP
    val c = rc % R_SEP
    val days = box[h][r][c]
    repeat(6) {
      val nh = h + dh[it]
      val nr = r + dr[it]
      val nc = c + dc[it]
      if (nh in 0 until hs && nr in 0 until rs && nc in 0 until cs && box[nh][nr][nc] == UNRIPE) {
        box[nh][nr][nc] = days + 1
        q[qt++] = nh * H_SEP + nr * R_SEP + nc
        unripeCnt--
        if (totalDays < days) totalDays = days
      }
    }
  }

  w(
    when {
      unripeCnt > 0 -> -1
      else -> totalDays
    }
  )
  O.flush()
}
