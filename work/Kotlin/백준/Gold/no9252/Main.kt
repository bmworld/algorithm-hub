package 백준.Gold.no9252

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

private fun b(): Byte {
  var b: Byte = 0
  while (b !in CAPITAL) {
    if (b == 10.toByte() || b == 32.toByte()) return END
    else b = r()
  }
  return b
}

private const val END: Byte = -1
private const val ALPHABET_SIZE = 26
private const val A = 65
private val CAPITAL = A until A + ALPHABET_SIZE
private const val MAX_LEN = 1_000
private const val POS_SEP = MAX_LEN * 10
private const val CNT_SEP = POS_SEP * POS_SEP
fun main() {
  var b: Byte = 0
  val str = ByteArray(MAX_LEN)
  var strLen = 0
  while (b().also { b = it } != END) str[strLen++] = b

  val tracer = Array(MAX_LEN + 1) { LongArray(strLen + 1) }

  var r = 1
  while (b().also { b = it } != END) {
    repeat(strLen) {
      val c = it + 1
      val a = str[it]
      var cnt: Long
      var tr = r - 1
      var tc = c - 1
      if (a == b) {
        cnt = tracer[r - 1][c - 1] / CNT_SEP + 1
      } else {
        val c1 = tracer[r - 1][c] / CNT_SEP
        val c2 = tracer[r][c - 1] / CNT_SEP
        if (c1 >= c2) {
          tr = r - 1
          tc = c
          cnt = c1
        } else {
          tr = r
          tc = c - 1
          cnt = c2
        }
      }
      tracer[r][c] = cnt * CNT_SEP + tr * POS_SEP + tc
    }
    r++
  }

  val WB = ByteArray(strLen)
  var WS = strLen - 1
  var tr = --r
  var tc = strLen
  while (WS >= 0) {
    val e = tracer[tr][tc]
    val pos = (e % CNT_SEP).toInt()
    val nr = pos / POS_SEP
    val nc = pos % POS_SEP
    val found = tr == nr + 1 && tc == nc + 1
    if (found) WB[WS--] = str[tc - 1]
    if (nr == 0 && nc == 0) break
    tr = nr
    tc = nc
  }
  WS++

  val maxLen = strLen - WS
  w(maxLen)
  O.write(10)
  O.write(WB, WS, maxLen)
  O.flush()
}
