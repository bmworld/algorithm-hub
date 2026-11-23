package 백준.Silver.no6236

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 18
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
  var l = 1
  var r = 0

  val N = i()
  val M = i()
  val cnds = IntArray(N) { i().also { if (it > r) r = it } }

  while (l <= r) {
    val m = (l + r) shr 1

    var t = 1
    var bal = m
    repeat(N) {
      val x = cnds[it]
      if (x > bal) {
        t++
        bal = m
      }
      bal -= x
    }

    when {
      t >= M -> l = m + 1
      else -> r = m - 1
    }
  }

  w(r)
  O.flush()
}
