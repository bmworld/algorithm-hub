package 백준.Gold.no21870

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 20
const val OBS = 1 shl 8
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

  val N = i()
  val a = IntArray(N)
  repeat(N) {
    a[it] = i()
  }

  var beatuiful = 0
  fun divideConquer(fr: Int, to: Int) {
    if (fr == to) {
      beatuiful += a[to]
      return
    }

    val half = (to - fr + 1) shr 1

    val frMid = fr + half - 1
    var frGcd = a[fr]
    for (i in fr + 1..frMid) frGcd = getGcd(frGcd, a[i])

    val toMid = to - half + 1
    var toGcd = a[toMid]
    for (i in toMid + 1..to) toGcd = getGcd(toGcd, a[i])

    if (frGcd >= toGcd) {
      beatuiful += frGcd
      divideConquer(frMid + 1, to)
    } else {
      beatuiful += toGcd
      divideConquer(fr, toMid - 1)
    }
  }

  divideConquer(0, N - 1)
  w(beatuiful)
  O.flush()
}

fun getGcd(a: Int, b: Int): Int = if (b == 0) a else getGcd(b, a % b)

//println(
//      "[$fr ~ $to ($half)] frGcd= $frGcd ($fr ~ $frMid) vs toGcd = $toGcd ($toMid ~ $to)")
