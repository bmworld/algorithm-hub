package 백준.Silver.no28279

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 16
private const val OBS = 1 shl 14
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
  O.write(WB, ++pos, WS - pos + 1)
}

private const val PUSH_FRONT = 1
private const val PUSH_BACK = 2
private const val POP_FRONT = 3
private const val POP_BACK = 4
private const val SIZE = 5
private const val EMPTY = 6
private const val FRONT = 7
private const val BACK = 8

fun main() {
  val N = i()
  val a = IntArray(2 * N - 1)
  var h = N - 1
  var t = h
  repeat(N) {
    val len = t - h
    when (i()) {
      PUSH_FRONT -> a[--h] = i()
      PUSH_BACK -> a[t++] = i()
      POP_FRONT -> w(if (len == 0) -1 else a[h++])
      POP_BACK -> w(if (len == 0) -1 else a[--t])
      SIZE -> w(len)
      EMPTY -> w(if (len == 0) 1 else 0)
      FRONT -> w(if (len == 0) -1 else a[h])
      BACK -> w(if (len == 0) -1 else a[t - 1])
    }
  }
  O.flush()
}
