package 백준.Silver.no13305

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 14
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
  num: Long
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

const val INF = 1_000_000_000_000_000_000L

fun main() {

  val N = i()
  val end = N - 1
  val dists = LongArray(N)
  repeat(N - 1) {
    dists[it + 1] = dists[it] + i()
  }

  var min = i().toLong()
  var total = (dists[end] - dists[0]) * min
  repeat(N - 1) {
    val i = it + 1
    val cost = i().toLong()
    if (cost >= min) return@repeat
    val rmnDist = dists[end] - dists[i]

    total -= (min - cost) * rmnDist
    min = cost
  }

  w(total)
  O.flush()
}
