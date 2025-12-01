package 백준.Bronze.no10989

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 19
private const val OBS = 1 shl 19
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
  var c: Byte
  while (r().also { c = it } in 48..57) v = v * 10 + (c - 48)
  return v
}


private const val WS = 5
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
  val arr = IntArray(10001)
  val n = i()
  var max = 0
  var min = Int.MAX_VALUE

  repeat(n) {
    val v = i()
    arr[v]++
    if (v > max) max = v
    if (v < min) min = v
  }

  for (v in min..max) {
    var cnt = arr[v]
    while (cnt-- > 0) w(v)
  }
  O.flush()
}
