package 백준.Bronze.no1159

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 8
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
  var c: Byte
  while (r().also { c = it } in NUM || c == 45.toByte()) {
    when (c) {
      in NUM -> v = v * 10 + c - 48
      else -> s = -1
    }
  }
  return s * v
}

val INVALID = "PREDAJA".toByteArray()
const val ALPH_CNT = 26
const val NL: Byte = 10
const val a: Byte = 97
const val EMPTY: Byte = 0
const val VALID_CNT = 5

fun main() {
  val CNT = IntArray(ALPH_CNT)
  var b: Byte
  repeat(i()) {
    var first = EMPTY
    while (r().also { b = it } >= a) if (first == EMPTY) first = b.also { CNT[it - a]++ }
  }

  val ans = ByteArray(ALPH_CNT)
  var len = 0
  repeat(ALPH_CNT) { pos ->
    if (CNT[pos] >= VALID_CNT) ans[len++] = (pos + a).toByte()
  }

  if (len == 0) O.write(INVALID)
  else O.write(ans, 0, len)
  O.flush()
}
