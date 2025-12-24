package 백준.Gold.no9251

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 10_000
private const val OBS = 2_000
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

private const val WS = 10
private val WB = ByteArray(WS)
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
  pos++
  O.write(WB, pos, WS - pos)
}

private val CAPITAL = 65..90
private const val MAX_LEN = 1_000
fun main() {
  var b: Byte = 0
  val str = ByteArray(MAX_LEN)
  var strLen = 0
  while (b !in CAPITAL) b = r()
  while (b in CAPITAL) {
    str[strLen++] = b
    b = r()
  }

  val prev = IntArray(strLen + 1)
  val cur = IntArray(strLen + 1)

  while (b !in CAPITAL) b = r()
  while (b in CAPITAL) {
    repeat(strLen) {
      val i = it + 1
      val a = str[it]
      prev[i] = cur[i]
      val comp = if (a == b) prev[i - 1] + 1
      else {
        val prevB = cur[i - 1]
        val prevA = prev[i]
        if (prevB > prevA) prevB else prevA
      }
      cur[i] = comp
    }
    b = r()
  }

  w(cur[strLen])
  O.flush()
}
