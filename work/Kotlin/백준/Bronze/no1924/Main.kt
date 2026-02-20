package 백준.Bronze.no1924

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 5
const val OBS = 1 shl 5
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
  var b: Byte
  while (r().also { b = it } in NUM || b == 45.toByte()) {
    when (b) {
      in NUM -> v = v * 10 + b - 48
      else -> s = -1
    }
  }
  return s * v
}

fun main() {
  val M = i()
  val D = i()
  var days = intArrayOf(0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334)[M - 1] + D - 1
  O.write(when (days % 7) {
    0 -> byteArrayOf(77, 79, 78)
    1 -> byteArrayOf(84, 85, 69)
    2 -> byteArrayOf(87, 69, 68)
    3 -> byteArrayOf(84, 72, 85)
    4 -> byteArrayOf(70, 82, 73)
    5 -> byteArrayOf(83, 65, 84)
    else -> byteArrayOf(83, 85, 78)
  })
  O.flush()
}
