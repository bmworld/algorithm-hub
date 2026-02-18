package 백준.Bronze.no5086

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 5
const val OBS = 1 shl 6
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

val FACTOR = byteArrayOf(102, 97, 99, 116, 111, 114, 10)
val MULTIPLE = byteArrayOf(109, 117, 108, 116, 105, 112, 108, 101, 10)
val NEITHER = byteArrayOf(110, 101, 105, 116, 104, 101, 114, 10)

fun main() {
  while (true) {
    val a = i()
    val b = i()
    if (a == 0 && b == 0) break
    when {
      b % a == 0 -> O.write(FACTOR)
      a % b == 0 -> O.write(MULTIPLE)
      else -> O.write(NEITHER)
    }
  }
  O.flush()
}
