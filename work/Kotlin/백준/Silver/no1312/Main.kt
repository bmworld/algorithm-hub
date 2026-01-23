package 백준.Silver.no1312

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.math.BigInteger

private const val IBS = 1 shl 5
private const val OBS = 1 shl 2
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

private val WB = ByteArray(WS)
private fun w(
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

  var A = i()
  var B = i()
  val N = i()

  val r = if (A == B) 0 else {
    A.toBigInteger()
      .multiply(BigInteger.TEN.pow(N))
      .divide(B.toBigInteger())
      .remainder(BigInteger.TEN)
      .toInt()
  }

  w(r)
  O.flush()
}
