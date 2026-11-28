package 백준.Gold.no1715

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
  O.write(10)
}

val SEP = 10_000L
fun main() {

  val n = i()
  val bundle = IntArray(n) { i() }

  qs(bundle, 0, n - 1)

  if (n > 1) bundle[1] += bundle[0]

  for (i in 2 until n)
    bundle[i] += bundle[i - 1] * 2

  w(bundle[n - 1])
  O.flush()
}


fun swap(
  a: IntArray,
  i: Int,
  j: Int,
) {
  val tmp = a[i]
  a[i] = a[j]
  a[j] = tmp
}

fun qs(
  a: IntArray,
  l: Int,
  r: Int,
) {
  if (l >= r) return

  var pos = l
  var pl = l
  var pr = r
  val piv = a[(l + r) shr 1]

  while (pos <= pr) {
    val x = a[pos]
    when {
      x < piv -> swap(a, pos++, pl++)
      x > piv -> swap(a, pos, pr--)
      else -> pos++
    }
  }
  qs(a, l, pl - 1)
  qs(a, pr + 1, r)
}
