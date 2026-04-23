package 백준.Silver.no2018

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 3
const val OBS = 1 shl 3
val I = BufferedInputStream(System.`in`)
val O = BufferedOutputStream(System.`out`, OBS)
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

const val ZERO: Byte = 48
val NUM = ZERO..ZERO + 9
fun i(): Int {
  var v = 0
  var b: Byte
  while (r().also { b = it } in NUM) v = v * 10 + b - ZERO
  return v
}

const val WS = 10
val WB = ByteArray(WS)
fun w(
  num: Int
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

fun main() {
  val N = i()
  var ans = 1

  var seq = 2
  while (true) {
    val m = N / seq
    val k = seq / 2
    val isEven = seq % 2 == 0
    val min = m - if (isEven) k - 1 else k
    if (min < 1) break
    val sum = if (isEven) (2 * m + 1) * k else 2 * m * k + m
    if (sum == N) ans++
    seq++
  }

  w(ans)
  O.flush()
}
