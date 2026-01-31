package 백준.Platium.no14003

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 18
const val OBS = 1 shl 18
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
val WB = ByteArray(WS + 1)
fun w(
  num: Int,
  end: Boolean = false
) {
  WB[WS] = if (end) 10 else 32
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
  O.write(WB, ++pos, WS - pos + 1)
}

fun main() {
  val N = i()
  val lis = IntArray(N)
  lis[0] = i()
  var li = 0
  repeat(N - 1) {
    val v = i()
    if (v > lis[li]) lis[++li] = v
    else updateLis(lis, v, 0, li)
  }

  val len = li + 1
  w(len, true)

  repeat(len) {
    w(lis[it])
  }

  O.flush()
}

fun updateLis(
  lis: IntArray,
  v: Int,
  stt: Int,
  end: Int
) {
  var l = stt
  var r = end
  while (l < r) {
    val m = (l + r) shr 1
    var mv = lis[m]
    when {
      mv < v -> l = m + 1
      else -> r = m
    }
  }
  lis[l] = v
}
