package 백준.Gold.no1931

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1_500_000
private const val OBS = 16
private const val EOF = -1
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0

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
  num: Int,
) {
  var v = num
  var end = WS - 1
  do {
    WB[end--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  val stt = end + 1
  O.write(WB, stt, -stt + WS)
}

private const val SEP = 10_000_000_000UL

@OptIn(ExperimentalUnsignedTypes::class)
fun main() {
  val n = i()
  val a = ULongArray(n)
  var i = 0
  var nt = Int.MAX_VALUE.toULong()
  repeat(n) {
    val f = i().toULong()
    val t = i().toULong()
    a[i++] = t * SEP + f
    if (t < nt) nt = t
  }
  a.sort()

  var cnt = 1
  repeat(n - 1) {
    val ul = a[it + 1]
    val t = ul / SEP
    val f = ul % SEP
    if (f >= nt) {
      cnt++
      nt = t
    }
  }

  w(cnt)
  O.flush()
}
