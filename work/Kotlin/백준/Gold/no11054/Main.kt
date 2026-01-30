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

fun main() {
  val N = i()
  val a = IntArray(N)
  val ascDP = IntArray(N)
  repeat(N) { i ->
    val v = i()
    a[i] = v
    var j = i - 1
    var maxCnt = 0
    while (j >= 0) {
      val t = a[j]
      if (t < v) {
        val acc = ascDP[j]
        if (maxCnt < acc) maxCnt = acc
        if (t == v - 1) break
      }
      j--
    }
    ascDP[i] = 1 + maxCnt
  }

  var max = 0
  val descDP = IntArray(N)
  repeat(N) {
    var i = N - 1 - it
    val v = a[i]
    var j = i + 1
    var maxCnt = 0
    while (j < N) {
      val t = a[j]
      if (t < v) {
        val acc = descDP[j]
        if (maxCnt < acc) maxCnt = acc
        if (t == v - 1) break
      }
      j++
    }
    descDP[i] = (1 + maxCnt).also {
      val total = ascDP[i] + it - 1
      if (total > max) max = total
    }
  }

  w(max)
  O.flush()
}
