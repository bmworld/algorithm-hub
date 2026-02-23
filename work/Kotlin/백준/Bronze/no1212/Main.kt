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
const val OCT_TO_BIN_SIZE = 3
val DELTA = intArrayOf(4, 2, 1)
fun main() {
  var b: Byte
  val BUF = ByteArray(3)
  var firstDigit = true
  while (r().also { b = it } >= NL) {
    if (b == NL) break
    var digit: Int = b - ZERO
    var offset = if (firstDigit) 2 else 0
    repeat(OCT_TO_BIN_SIZE) { i ->
      val delta = DELTA[i]
      if (digit >= delta) {
        BUF[i] = ONE
        digit -= delta
        if (firstDigit) offset = minOf(offset, i)
      } else BUF[i] = ZERO
    }
    O.write(BUF, offset, OCT_TO_BIN_SIZE - offset)
    firstDigit = false
  }

  O.flush()
}
