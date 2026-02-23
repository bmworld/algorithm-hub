package 백준.Bronze.no1212

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

const val NL: Byte = 10
const val ZERO: Byte = 48
const val ONE: Byte = 49
const val MAX_LEN = 333_334
val DELTA = intArrayOf(4, 2, 1)
fun main() {
  val OCT = IntArray(MAX_LEN)
  var b: Byte
  var oLen = 0
  while (r().also { b = it } >= NL) {
    if (b == NL) break
    OCT[oLen++] = b - ZERO
  }

  val OCT_TO_BIN_SIZE = 3
  val bLen = oLen * OCT_TO_BIN_SIZE
  val BIN = ByteArray(bLen) { ZERO }

  var offset = bLen - 1
  repeat(oLen) { i ->
    var digit = OCT[oLen - (i + 1)]
    val end = bLen - (OCT_TO_BIN_SIZE * i + 1)
    repeat(OCT_TO_BIN_SIZE) { j ->
      val bi = end - OCT_TO_BIN_SIZE + 1 + j
      val delta = DELTA[j]
      if (digit >= delta) {
        BIN[bi] = ONE
        digit -= delta
        offset = minOf(offset, bi)
      }
    }
  }

  O.write(BIN, offset, bLen - offset)
  O.flush()
}
