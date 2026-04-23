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
  var ans = 0
  var l = 1
  var r = 1
  var sum = 1
  while (r <= N) {
    when {
      sum < N -> sum += ++r
      sum > N -> sum -= l++
      else -> {
        ans++
        sum += ++r
        sum -= l++
      }
    }
  }

  w(ans)
  O.flush()
}
