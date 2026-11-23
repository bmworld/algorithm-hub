package 백준.Silver.no2512

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 16
const val OBS = 1 shl 4
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
  var b: Byte
  while (r().also { b = it } in NUM || b == 45.toByte()) {
    when (b) {
      in NUM -> v = v * 10 + b - 48
      else -> s = -1
    }
  }
  return s * v
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
  val n = i()
  var max = 0
  val arr = IntArray(n) { i().also { if (it > max) max = it } }
  val bdgt = i()
  w(bs(arr, 1, max, bdgt))
  O.flush()
}

fun bs(arr: IntArray, min: Int, max: Int, bdgt: Int): Int {

  var l = min
  var r = minOf(max, bdgt)

  while (l <= r) {
    var rmn = bdgt
    val m = (l + r) shr 1
    for (x in arr) {
      rmn -= minOf(x, m)
      if (rmn < 0) break
    }

    when {
      rmn < 0 -> r = m - 1
      rmn > 0 -> l = m + 1
      else -> return m
    }
  }

  return r
}
