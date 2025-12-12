package 백준.Bronze.no1236

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1_500
private const val OBS = 1_000
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

private const val GUARD = 88.toByte()

private fun s(): Boolean {
  var c: Byte
  while (r().also { c = it } <= 32) {
  }
  return c == GUARD
}

fun main() {
  val r = i()
  val c = i()
  var tr = r
  var tc = c
  val colCh = BooleanArray(c)
  repeat(r) {
    var rowCh = false
    repeat(c) {
      val guard = s()
      if (!rowCh && guard) rowCh = true
      if (!colCh[it] && guard) colCh[it] = true
    }
    if (rowCh) tr--
  }

  repeat(c) {
    if (colCh[it]) tc--
  }

  w(if (tr > tc) tr else tc)
  O.flush()
}
