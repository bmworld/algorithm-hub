package 백준.Silver.no1120

import java.io.BufferedInputStream

const val IBS = 102
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

const val MAX = 50
const val a: Byte = 97
fun main() {
  var b: Byte
  val A = ByteArray(MAX)
  var ALen = 0
  while (r().also { b = it } >= a) A[ALen++] = b

  val B = ByteArray(MAX)
  var BLen = 0
  while (r().also { b = it } >= a) B[BLen++] = b

  var minDiff = ALen
  repeat(BLen - ALen + 1) { fr ->
    var diff = 0
    for (ai in 0 until ALen) {
      val bi = ai + fr
      if (A[ai] != B[bi]) {
        if (++diff >= minDiff) break
      }
    }
    if (minDiff > diff) minDiff = diff
  }

  print(minDiff)
}
