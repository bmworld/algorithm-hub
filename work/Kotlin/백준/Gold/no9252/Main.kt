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
private const val SEP = 10000
fun main() {
  var b: Byte = 0
  val str = ByteArray(MAX_LEN)
  var strLen = 0
  while (b().also { b = it } != END) str[strLen++] = b

  val lcs = Array(MAX_LEN + 1) { IntArray(strLen + 1) }
  val tracer = Array(MAX_LEN + 1) { IntArray(strLen + 1) }

  var r = 1
  while (b().also { b = it } != END) {
    repeat(strLen) {
      val c = it + 1
      val a = str[it]

      var tr = r - 1
      var tc = c - 1

      lcs[r][c] = if (a == b) lcs[r - 1][c - 1] + 1 else {
        val c1 = lcs[r - 1][c]
        val c2 = lcs[r][c - 1]
        if (c1 >= c2) {
          tr = r - 1
          tc = c
          c1
        } else {
          tr = r
          tc = c - 1
          c2
        }
      }
      tracer[r][c] = tr * SEP + tc
    }
    r++
  }

  var maxLen = lcs[--r][strLen]
  w(maxLen)
  O.write(10)

  val WB = ByteArray(maxLen)
  var tr = r
  var tc = strLen
  while (maxLen > 0) {
    val np = tracer[tr][tc]
    val nr = np / SEP
    val nc = np % SEP
    if (nr == 0 && nc == 0) break
    val found = tr == nr + 1 && tc == nc + 1
    if (found) WB[--maxLen] = str[tc - 1]
    tr = nr
    tc = nc
  }
  O.write(WB)
  O.flush()
}
