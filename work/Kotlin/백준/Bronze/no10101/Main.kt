package 백준.Bronze.no10101

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 5
const val OBS = 1 shl 4
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

val ERROR = "Error".toByteArray()
val EQ = "Equilateral".toByteArray()
val IS = "Isosceles".toByteArray()
val SL = "Scalene".toByteArray()
fun main() {
  val a = i()
  val b = i()
  val c = i()
  O.write(when {
    a + b + c == 180 -> when {
      a == b && b == c -> EQ
      a == b || b == c || a == c -> IS
      else -> SL
    }
    else -> ERROR
  })
  O.flush()
}
