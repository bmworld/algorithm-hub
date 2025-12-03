package 백준.Bronze.no16172

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 200_000
private const val OBS = 1
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

private const val WS = 10
private val WB = ByteArray(WS)

private fun w(
  num: Int,
) {
  var v = num
  var end = WS - 1
  do {
    WB[end--] = ((v % 10) + 48).toByte()
    v /= 10
  } while (v > 0)
  val stt = end + 1
  O.write(WB, stt, -stt + WS)
}

private const val MAX_LEN = 200_000

fun main() {
  var found = false
  val a = ByteArray(MAX_LEN)
  var c: Byte
  var aLen = 0
  while (r().also { c = it } >= 32.toByte()) {
    when (c) {
      in NUM -> continue
      else -> a[aLen++] = c
    }
  }

  val b = ByteArray(MAX_LEN)
  var bLen = 0
  while (r().also { c = it } >= 65.toByte()) b[bLen++] = c

  val diffLen = aLen - bLen

  outer@ for (i in 0..diffLen) {
    var ch = true
    for (l in 0 until (bLen + 1) / 2) {
      val r = bLen - 1 - l
      if (a[i + l] != b[l] || b[r] != a[i + r]) {
        ch = false
        break
      }
    }
    if (ch) {
      found = true
      break@outer
    }
  }

  w(if (found) 1 else 0)
  O.flush()
}
