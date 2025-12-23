package 백준.Gold.no9251

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 2_002
private const val OBS = 1_000
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

private const val A = 65
private const val ALPH_LEN = 26
private val CAPITAL = A..<A + ALPH_LEN
private const val MAX_LEN = 1_000
fun main() {
  var c = r()
  val a = ByteArray(MAX_LEN)
  var aLen = 0
  val cnts = IntArray(ALPH_LEN)
  while (c !in CAPITAL) c = r()
  while (c in CAPITAL) {
    a[aLen++] = c
    cnts[c - A]++
    c = r()
  }

  val dp = IntArray(aLen)
  while (c !in CAPITAL) c = r()
  while (c in CAPITAL) {
    var matched = false
    repeat(aLen) { i ->
      if (a[i] == c && cnts[c - A] > 0) dp[i]++.also { matched = true; }
      else if (matched) dp[i] = dp[i - 1]
    }
    cnts[c - A]--
    c = r()
  }

  w(dp[aLen - 1])
  O.flush()
}
