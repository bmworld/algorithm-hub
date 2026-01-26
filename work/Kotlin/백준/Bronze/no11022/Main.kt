package 백준.Bronze.no11022

import java.io.BufferedOutputStream
import java.io.DataInputStream

const val IBS = 1 shl 8
const val OBS = 1 shl 10
val O = BufferedOutputStream(System.`out`, OBS)
val I = DataInputStream(System.`in`)
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

val tag = byteArrayOf(67, 97, 115, 101, 32, 35)
val plus = byteArrayOf(32, 43, 32)
val equal = byteArrayOf(32, 61, 32)
val SEP = byteArrayOf(58, 32)
const val NL = 10
fun main() {
  repeat(i()) {
    var no = it + 1
    val a = i()
    val b = i()

    O.write(tag)
    w(no)
    O.write(SEP)
    w(a)
    O.write(plus)
    w(b)
    O.write(equal)
    w(a + b)
    O.write(NL)
  }

  O.flush()
}
