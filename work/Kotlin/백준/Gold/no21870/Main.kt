package 백준.Gold.no21870

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 16
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

  fun divideConquer(l: Int, r: Int): Int {
    if (l == r) return a[r]

    val half = (r - l + 1) shr 1
    val m = l + half - 1
    var lGcd = a[l]
    for (i in l + 1..m) lGcd = getGcd(lGcd, a[i])
    lGcd += divideConquer(m + 1, r)

    var rGcd = a[m + 1]
    for (i in m + 2..r) rGcd = getGcd(rGcd, a[i])
    rGcd += divideConquer(l, m)

    return maxOf(lGcd, rGcd)
  }

  w(divideConquer(0, N - 1))
  O.flush()
}

fun getGcd(a: Int, b: Int): Int = if (b == 0) a else getGcd(b, a % b)

//println( "[$fr ~ $to ($half)] frGcd= $frGcd ($fr ~ $mid) vs toGcd = $toGcd (${mid + 1} ~ $to)")

/**
IN
6
6 6 6 5 10 15
OUT
31
 */
