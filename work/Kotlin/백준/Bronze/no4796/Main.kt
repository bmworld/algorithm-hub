package 백준.Bronze.no4796

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 10
const val OBS = 1 shl 10
val O = BufferedOutputStream(System.`out`, OBS)
val I = BufferedInputStream(System.`in`)
val IB = ByteArray(IBS)
var Ii = 0
var Il = 0
const val EOF = -1

fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

val NUM = 48..57
fun i(): Int {
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

const val WS = 10
val WB = ByteArray(WS + 1).also { it[WS] = 10 }
fun w(num: Int, useNL: Boolean = false) {
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
  O.write(WB, ++pos, WS - pos + if (useNL) 1 else 0)
}

val PREFIX = "Case ".toByteArray()
val COLON = ": ".toByteArray()
fun main() {
  var case = 1
  while (true) {
    val L = i()
    val P = i()
    val V = i()
    if (L == 0 || P == 0 || V == 0) break

    O.write(PREFIX)
    w(case++)
    O.write(COLON)
    w(V / P * L + minOf(L, V % P), true)
  }

  O.flush()
}

/**
IN
2 12 33
2 20 198
0 0 0

OUT
Case 1: 6
Case 2: 20
 */
