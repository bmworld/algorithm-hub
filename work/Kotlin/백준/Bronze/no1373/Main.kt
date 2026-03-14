package 백준.Bronze.no1373

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 16
const val OBS = 1 shl 16
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

const val MAX_LEN = 1_000_000
const val NL: Byte = 10
const val ZERO: Byte = 48
const val ONE: Byte = 49

fun main() {
  var b: Byte

  val BIN = BooleanArray(MAX_LEN)
  var bLen = 0
  while (r().also { b = it } >= ZERO) BIN[bLen++] = b == ONE
  var bi = bLen

  var oLen = (bLen + 2) / 3
  val OCT = ByteArray(oLen)

  var digit = 0
  repeat(bLen) {
    val idx = it % 3
    digit += (1 shl idx) * if (BIN[--bi]) 1 else 0
    if (it + 1 == bLen || idx == 2) {
      OCT[--oLen] = (digit + ZERO).toByte()
      digit = 0
    }
  }

  O.write(OCT, 0, OCT.size)
  O.flush()
}
