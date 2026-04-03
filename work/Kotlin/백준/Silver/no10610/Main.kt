package 백준.Silver.no10610

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 17
const val OBS = 1 shl 17
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

const val ZERO: Byte = 48
const val DIGITS = 10
fun main() {

  var digitCnt = 0
  val COUNTER = IntArray(DIGITS)
  var b: Byte
  while (r().also { b = it } >= ZERO) {
    COUNTER[b - ZERO]++
    digitCnt++
  }

  if (COUNTER[0] == 0 ||
    (COUNTER[8] + COUNTER[5] + COUNTER[2]) % 3
    != (COUNTER[7] + COUNTER[4] + COUNTER[1]) % 3
  ) {
    O.write('-'.code)
    O.write(ZERO + 1)
  } else {
    val ANS = ByteArray(digitCnt)
    COUNTER[0]--
    ANS[digitCnt - 1] = ZERO

    var i = 0
    repeat(DIGITS) {
      val digit = DIGITS - (1 + it)
      val cnt = COUNTER[digit]
      repeat(cnt) {
        ANS[i++] = (digit + ZERO).toByte()
      }
    }
    O.write(ANS)
  }

  O.flush()
}

/*
IN
143007
OUT
743100

IN
2171073
OUT
7732110
 */
