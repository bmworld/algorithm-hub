package 백준.Silver.no1475

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 3
private const val OBS = 1 shl 3
private val O = BufferedOutputStream(System.out, OBS)
private val I = BufferedInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0
private const val EOF = -1
private const val NL: Byte = 10

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
  O.write(WB, ++pos, WS - pos)
}

fun main() {
  val cnts = IntArray(10)

  var c: Byte
  while (r().also { c = it } >= NL) {
    if (c == NL) break
    cnts[c - 48]++
  }

  var maxEx69 = 0
  repeat(10) { i ->
    if (i == 6 || i == 9) return@repeat
    val cnt = cnts[i]
    if (maxEx69 < cnt) maxEx69 = cnt
  }
  val max69 = (cnts[6] + cnts[9] + 1) / 2
  w(if (max69 > maxEx69) max69 else maxEx69)

  O.flush()
}
