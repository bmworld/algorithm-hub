package 백준.Bronze.no10807

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 10
private const val OBS = 1 shl 3
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

private fun i(): Int {
  var v = 0
  var c = r()
  var sign = 1
  if (c == 45.toByte()) {
    sign = -1
    c = r()
  }
  while (c in 48..57) {
    v = v * 10 + (c - 48)
    c = r()
  }
  return v * sign
}

private const val WS = 10
private val WB = ByteArray(WS)

private fun w(
  num: Int,
) {
  var x = num
  var end = WS - 1
  do {
    WB[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = end + 1
  O.write(WB, stt, WS - stt)
}

fun main() {
  val a = IntArray(201)

  repeat(i()) {
    a[i() + 100]++
  }
  w(a[i() + 100])
  O.flush()
}
