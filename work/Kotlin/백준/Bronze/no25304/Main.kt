package 백준.Bronze.no25304

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1_100
const val OBS = 3
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


val YES = byteArrayOf(89, 101, 115)
val NO = byteArrayOf(78, 111)
fun main() {
  var X = i()
  repeat(i()) {
    X -= i() * i()
  }
  O.write(if (X == 0) YES else NO)
  O.flush()
}
