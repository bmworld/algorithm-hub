package 백준.Bronze.no5597

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 7
private const val OBS = 1 shl 2
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
  while (c in 48..57) {
    v = v * 10 + (c - 48)
    c = r()
  }
  return v
}

private const val WS = 2
private val WB = ByteArray(WS + 1).also { it[WS] = '\n'.code.toByte() }

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
  O.write(WB, stt, WS - stt + 1)
}

fun main() {
  val a = BooleanArray(30)
  repeat(28) {
    a[i() - 1] = true
  }
  repeat(30) { i ->
    if (!a[i]) w(i + 1)
  }
  O.flush()
}
