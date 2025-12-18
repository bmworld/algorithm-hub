package 백준.Silver.no5525

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1_000_000
private const val OBS = (IBS + 3) / 3
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
  var end = WS - 1
  do {
    WB[end--] = ((v % 10) + 48).toByte()
    v /= 10
  } while (v > 0)
  val stt = end + 1
  O.write(WB, stt, -stt + WS)
}

private const val i = 73.toByte()
private const val o = 79.toByte()

fun main() {
  val n = i()
  val m = i()
  var minI = m
  var maxI = 0
  val s = ByteArray(m)
  repeat(m) { idx ->
    val c = r()
    s[idx] = c
    if (c == i) {
      if (idx < minI) minI = idx
      if (idx > maxI) maxI = idx
    }
  }

  if (minI == m) {
    w(0)
    O.flush()
    return
  }

  var cnt = 0
  val goal = 2 * n + 1
  var seq = 0
  repeat(maxI - minI + 1) {
    val r = minI + it
    val c = s[r]
    val even = seq % 2 == 0
    val valid = even && c == i || !even && c == o
    if (!valid) {
      seq = if (c == i) 1 else 0
    } else if (++seq == goal) {
      cnt++
      seq -= 2
    }
  }

  w(cnt)
  O.flush()
}
