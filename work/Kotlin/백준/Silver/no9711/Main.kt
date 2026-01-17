package 백준.Silver.no9711

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 10
private const val OBS = 1 shl 10
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = BufferedInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0
private const val EOF = -1

private fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

private val NUM = 48..57
private fun i(): Int {
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

private const val WS = 10
private val WB = ByteArray(WS + 1).also { it[WS] = 10 }
private fun w(
  num: Int,
  nl: Boolean = true
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
  O.write(WB, ++pos, WS - pos + if (nl) 1 else 0)
}

private val TAG = byteArrayOf(67, 97, 115, 101, 32, 35)
private val COL = byteArrayOf(58, 32)
private const val SEP = 10_000_000_000
private const val MAX = 10_000
fun main() {
  val a = LongArray(MAX + 1)
  a[1] = 1
  a[2] = 1

  fun fib(
    fr: Int = 3,
    to: Int
  ): Long = a.also {
    repeat(to - fr + 1) { i ->
      val n = i + fr
      it[n] = ((it[n - 1] + it[n - 2]) % SEP)
    }
  }[to]

  var maxP = 2
  repeat(i()) {
    val x = it + 1
    val p = i()
    val q = i()

    O.write(TAG)
    w(x, false)
    O.write(COL)
    w(((if (p <= maxP) a[p] else fib(maxP + 1, p)) % q).toInt())

    if (p > maxP) maxP = p
  }
  O.flush()
}
