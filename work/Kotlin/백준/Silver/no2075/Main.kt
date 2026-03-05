package 백준.Silver.no2075

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 14
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

const val SEP = 10_000L
const val MIN = Int.MIN_VALUE
const val HALF = 1_000_000_000
fun main() {

  val N = i()
  val CAP = N
  fun pos(r: Int, c: Int): Int = r * CAP + c

  val a = IntArray(N * N) { i() }
  val cnds = LongArray(N).also {
    repeat(N) { c ->
      it[c] = (a[pos(N - 1, c)] + HALF) * SEP + (N - 1)
    }
  }

  repeat(N - 1) {
    var max = MIN
    var c = -1
    var r = -1
    repeat(N) { col ->
      val e = cnds[col]
      val v = e / SEP - HALF
      if (v > max) {
        max = v.toInt()
        r = (e % SEP).toInt()
        c = col
      }
    }
    cnds[c] = (a[pos(r - 1, c)] + HALF) * SEP + (r - 1)
  }

  var ans = MIN
  repeat(N) {
    val v = (cnds[it] / SEP - HALF).toInt()
    ans = maxOf(ans, v)
  }
  w(ans)
  O.flush()
}
