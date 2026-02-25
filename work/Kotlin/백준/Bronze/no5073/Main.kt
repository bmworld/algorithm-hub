package 백준.Bronze.no5073

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

val INVALID = "Invalid\n".toByteArray()
val EQ = "Equilateral\n".toByteArray()
val IS = "Isosceles\n".toByteArray()
val SL = "Scalene\n".toByteArray()
fun main() {

  while (true) {
    val a = i()
    val b = i()
    val c = i()
    if (a == 0 || b == 0 || c == 0) break
    val max = maxOf(a, b, c)
    val min = minOf(a, b, c)
    val mid = a + b + c - max - min

    O.write(when {
      max >= min + mid -> INVALID
      max == mid && mid == min -> EQ
      max == mid || mid == min -> IS
      else -> SL
    })
  }
  O.flush()
}
