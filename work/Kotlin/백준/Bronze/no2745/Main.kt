package 백준.Bronze.no2745

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 8
const val OBS = 1 shl 8
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

const val ZERO: Byte = 48
const val A: Byte = 65
const val Z: Byte = 90
const val INT_BITS = 32
const val SPACE: Byte = 32
fun main() {
  val NUM = IntArray(INT_BITS)
  var b: Byte
  var len = 0
  while (r().also { b = it } >= SPACE) {
    if (b == SPACE) break
    NUM[len++] = when (b) {
      in A..Z -> b - A + 10
      else -> b - ZERO
    }
  }

  val base = i()
  var pow = 1
  var num = 0
  repeat(len) {
    num += NUM[len - (it + 1)] * pow
    pow *= base
  }

  w(num)
  O.flush()
}
