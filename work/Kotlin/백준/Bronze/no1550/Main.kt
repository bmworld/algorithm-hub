package 백준.Bronze.no1550

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 3
const val OBS = 1 shl 3
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


const val WS = 10
val WB = ByteArray(WS)
fun w(
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
  O.write(WB, ++pos, WS - pos)
}

const val MAX = 6
const val ZERO = 48
const val NINE = 57
const val A = 65

fun main() {
  val NUM = IntArray(6)

  var len = 0
  var b: Byte
  while (r().also { b = it } >= ZERO) NUM[len++] = if (b <= NINE) b - ZERO else b - A + 10

  var ans = 0
  var base = 1
  repeat(len) {
    ans += NUM[--len] * base
    base *= 16
  }

  w(ans)
  O.flush()
}
