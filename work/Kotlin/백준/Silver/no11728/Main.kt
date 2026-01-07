package 백준.Silver.no11728

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 16
private const val OBS = 1 shl 16
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
  pos++
  O.write(WB, pos, WS - pos + 1)
}

private const val MAX = 1_000_000_001
fun main() {
  val aLen = i()
  val bLen = i()
  val a = IntArray(aLen + 1)
  a[aLen] = MAX
  repeat(aLen) {
    a[it] = i()
  }

  val b = IntArray(bLen + 1)
  b[bLen] = MAX
  repeat(bLen) {
    b[it] = i()
  }

  var ai = 0
  var bi = 0

  while (ai <= aLen && bi <= bLen) {
    val av = a[ai]
    val bv = b[bi]
    if (av < bv) {
      w(av)
      ai++
    } else if (av == MAX && bv == MAX) break
    else {
      w(bv)
      bi++
    }
  }

  O.flush()
}
