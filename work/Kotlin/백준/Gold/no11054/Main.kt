package 백준.Gold.no11054

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 12
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

const val EMPTY = -1
fun main() {
  var max = 1

  val N = i()
  val last = N - 1
  val a = IntArray(N) { i() }
  val lLis = IntArray(N)
  var li = EMPTY
  val lis = IntArray(N)
  repeat(N) { l ->
    val lv = a[l]
    if (li == EMPTY || lv > lLis[li]) lLis[++li] = lv
    else updateLis(lLis, lv, 0, li)
    lis[l] = (li + 1).also { if (it > max) max = it }
  }

  val rLis = IntArray(N)
  var ri = EMPTY
  repeat(N) { l ->
    val r = last - l
    val rv = a[r]
    if (ri == EMPTY || rv > rLis[ri]) rLis[++ri] = rv
    else updateLis(rLis, rv, 0, ri)
    lis[r] = (lis[r] + ri).also { total -> if (total > max) max = total }

  }

  w(max)
  O.flush()
}

fun updateLis(
  lis: IntArray,
  v: Int,
  stt: Int,
  end: Int
) {
  for (i in stt..end) {
    if (v <= lis[i]) {
      lis[i] = v
      break
    }
  }
}
