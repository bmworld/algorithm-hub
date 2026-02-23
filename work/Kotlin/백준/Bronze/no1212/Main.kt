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
const val OCT_TO_BIN_SIZE = 3
val DELTA = intArrayOf(4, 2, 1)
fun main() {
  var b: Byte
  val BIN = ByteArray(MAX_LEN * 3) { ZERO }
  var len = 0
  var offset = 2
  while (r().also { b = it } >= NL) {
    if (b == NL) break
    var digit: Int = b - ZERO
    repeat(OCT_TO_BIN_SIZE) { j ->
      val i = len + j
      val delta = DELTA[j]
      if (digit >= delta) {
        BIN[i] = ONE
        digit -= delta
        if (len == 0) offset = minOf(offset, i)
      }
    }
    len += OCT_TO_BIN_SIZE
  }

  O.write(BIN, offset, len - offset)
  O.flush()
}
