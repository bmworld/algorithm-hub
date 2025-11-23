package 백준.Silver.no2630

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 15
private const val OBS = 1 shl 11
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
private val IB = ByteArray(IBS)
private const val EOF = -1
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
  var n = 0
  var c: Byte
  while (r().also { c = it } in 48..57) n = n * 10 + (c - 48)
  return n
}

private fun b(): Byte {
  var c = r()
  while (c !in 48..49) c = r()
  return if (c == ZERO) WH else BL
}

private const val WS = 10
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

private const val ZERO = 48.toByte()
private const val BL = 1.toByte()
private const val WH = 0.toByte()
private const val CHECKED = (-1).toByte()

fun main() {

  val n = i()
  val a = Array(n) { ByteArray(n) }
  for (y in 0 until n) for (x in 0 until n) a[y][x] = b()

  var whCnt = 0
  var blCnt = 0

  fun chk(y: Int, x: Int) {
    val v = a[y][x]
    if (v == CHECKED) return
    if (v == BL) blCnt++ else whCnt++

    var w = 0
    while (x + w < n) {
      val nw = w + 1
      val nx = x + nw
      val ny = y + nw
      if (nx >= n || ny >= n || a[ny][x] != v || a[y][nx] != v || a[ny][nx] != v) break else w++
    }

    for (ny in y + w downTo y) for (nx in x + w downTo x) a[ny][nx] = CHECKED
  }

  for (y in 0 until n) for (x in 0 until n) chk(y, x)

  w(whCnt)
  w(blCnt)
  O.flush()
}
