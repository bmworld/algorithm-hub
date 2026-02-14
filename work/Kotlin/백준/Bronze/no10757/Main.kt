package 백준.Bronze.no10757

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 20_002
const val OBS = 10_000
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

const val MAX_LEN = 10_001
const val NL: Byte = 10
const val SPACE: Byte = 32
const val ZERO: Byte = 48
fun main() {
  var c: Byte
  val a = IntArray(MAX_LEN)
  var aLen = 0
  while (r().also { c = it } >= SPACE) {
    if (c == SPACE) break
    a[aLen++] = c - ZERO
  }

  val b = IntArray(MAX_LEN)
  var bLen = 0
  while (r().also { c = it } >= NL) {
    if (c == NL) break
    b[bLen++] = c - ZERO
  }

  val num = ByteArray(MAX_LEN)
  var len = maxOf(aLen, bLen)
  val bigger = if (aLen >= bLen) a else b

  repeat(len) {
    val ni = MAX_LEN - 1 - it
    val i = len - 1 - it
    val ai = aLen - 1 - it
    val bi = bLen - 1 - it
    val an = if (ai >= 0) a[ai] else 0
    val bn = if (bi >= 0) b[bi] else 0

    var cur = an + bn
    if (cur >= 10) {
      if (i > 0) bigger[i - 1]++
      cur -= 10
      if (it == len - 1) {
        num[ni - 1] = (1 + ZERO).toByte()
        len++
      }
    }
    bigger[i] = cur
    num[ni] = (cur + ZERO).toByte()
  }


  O.write(num, MAX_LEN - len, len)
  O.flush()
}

//    println("bigger[$i] = $an + $bn = ${bigger[i]}")
