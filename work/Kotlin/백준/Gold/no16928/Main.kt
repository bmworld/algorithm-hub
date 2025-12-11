package 백준.Gold.no16928

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 5_000
private const val OBS = 10
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
  pos++
  O.write(WB, pos, WS - pos)
}


private const val SEP = 100
private const val GOAL = 100
fun main() {
  val n = i()
  val m = i()
  val a = IntArray(GOAL + 1) { it }
  val cnts = IntArray(GOAL + 1) { GOAL }

  repeat(n) {
    a[i()] = i()
  }
  repeat(m) {
    a[i()] = i()
  }

  val q = IntArray(GOAL)
  var qh = 0
  var qt = 0
  q[qt++] = 1 * SEP + 0

  while (qh < qt) {
    val v = q[qh++]
    val pos = v / SEP
    val cnt = v % SEP
    val rootPos = a[pos]
    val nc = cnt + 1
    repeat(6) {
      val jump = it + 1
      val np = rootPos + jump
      if (np > GOAL || cnts[np] <= nc || a[np] < np) return@repeat
      cnts[np] = nc
      q[qt++] = np * SEP + nc
    }
  }

  w(cnts[GOAL])
  O.flush()
}
