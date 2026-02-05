package 백준.Silver.no10799

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 16
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

const val WS = 20
val WB = ByteArray(WS)
fun w(
  num: Long,
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

const val NL: Byte = 10
const val L: Byte = 40
const val R: Byte = 41
const val MAX_LEN = 100_000
fun main() {
  var cnt = 0L

  var plates = 0
  var prv: Byte = 0
  var cur: Byte = 0
  while (r().also { cur = it } >= NL) {
    when (cur) {
      L -> plates++
      R -> {
        plates--
        cnt += if (prv == L) plates else 1
      }
      else -> break
    }
    prv = cur
  }

  w(cnt)
  O.flush()
}
