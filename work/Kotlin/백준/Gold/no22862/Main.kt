package 백준.Gold.no22862

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 22
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
  var c: Byte
  while (r().also { c = it } in NUM || c == 45.toByte()) {
    when (c) {
      in NUM -> v = v * 10 + c - 48
      else -> s = -1
    }
  }
  return s * v
}

const val WS = 10
val WB = ByteArray(WS)
fun w(
  num: Int,
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
  val K = i()
  var lastEvenPos = 0
  val a = IntArray(N) { i ->
    i().also { if (it % 2 == 0) lastEvenPos = i }
  }

  var max = 0
  var odds = 0
  var evens = 0

  var l = 0
  for (r in 0..lastEvenPos) {
    if (a[r] % 2 == 0) evens++ else odds++
    while (odds > K) if (a[l++] % 2 == 0) evens-- else odds--
    max = maxOf(max, evens)
  }

  w(max)
  O.flush()
}
