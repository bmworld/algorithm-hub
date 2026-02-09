package 백준.Gold.no1030

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 8
const val OBS = 1 shl 15
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

const val NL: Byte = 10
const val WH: Byte = 48
const val BK: Byte = 49

fun main() {
  val S = i()
  val N = i()
  val K = i()
  val r1 = i()
  val r2 = i()
  val c1 = i()
  val c2 = i()
  val H = r2 - r1 + 1
  val W = c2 - c1 + 1
  val CAP = W + 1

  val canvas = ByteArray(CAP * H) {
    if (it % CAP == CAP - 1) NL else WH
  }

  fun pos(r: Int, c: Int): Int = (r - r1) * CAP + (c - c1)
  fun inRange(rowFr: Int, rowTo: Int, colFr: Int, colTo: Int): Boolean =
    rowFr <= r2 && rowTo >= r1 && colFr <= c2 && colTo >= c1

  val kStt = (N - K) shr 1
  val kEnd = N - 1 - kStt
  fun useBK(color: Byte, ri: Int, ci: Int): Boolean =
    color == BK || ri in kStt..kEnd && ci in kStt..kEnd

  fun rcsv(size: Int, r: Int, c: Int, color: Byte) {
    if (size == 1) {
      if (color == BK) canvas[pos(r, c)] = color
      return
    }

    var ns = size / N
    repeat(N) { ri ->
      repeat(N) { ci ->
        var nrFr = r + ri * ns
        var nrTo = nrFr + ns - 1
        var ncFr = c + ci * ns
        var ncTo = ncFr + ns - 1

        if (inRange(nrFr, nrTo, ncFr, ncTo))
          rcsv(ns, nrFr, ncFr, if (useBK(color, ri, ci)) BK else WH)
      }
    }
  }

  rcsv(pow(N, S), 0, 0, WH)
  O.write(canvas)
  O.flush()
}

fun pow(base: Int, exp: Int): Int {
  var v = 1
  var b = base
  var e = exp
  while (e > 0) {
    if (e % 2 == 1) v = v * b
    b *= b
    e /= 2
  }
  return v
}
