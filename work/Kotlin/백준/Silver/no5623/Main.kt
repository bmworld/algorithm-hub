package 백준.Silver.no5623

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 15
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
private val WB = ByteArray(WS + 1).also { it[WS] = 32 }
private fun w(
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
  O.write(WB, ++pos, WS - pos + 1)
}

fun main() {
  val N = i()
  if (N <= 2) {
    w(1)
    w(1)
    O.flush()
    return
  }

  val a = IntArray(N) { 1 }
  var r0c1 = 0
  var r0c2 = 0
  repeat(2) { r ->
    repeat(N) { c ->
      val sum = i()
      when (r) {
        0 -> when (c) {
          0 -> {}
          1 -> r0c1 = sum
          2 -> r0c2 = sum
        }
        1 -> if (c >= 2) {
          if (c == 2) {
            a[1] = (sum - r0c2 + r0c1) / 2
            a[0] = r0c1 - a[1]
          }
          a[c] = sum - a[1]
          val end = c == N - 1
          if (end) {
            repeat(N) {
              w(a[it])
            }
            O.flush()
            return
          }
        }
      }
    }
  }
}
