package 백준.Silver.no1213

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 6
const val OBS = 1 shl 6
val O = BufferedOutputStream(System.`out`, OBS)
val I = BufferedInputStream(System.`in`)
val IB = ByteArray(IBS)
var Ii = 0
var Il = 0
const val EOF = -1

fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

const val NL: Byte = 10
const val A: Byte = 65
const val EMPTY: Byte = -1
const val ALPHBET_CNT = 26
val IMPOSSIBLE = "I'm Sorry Hansoo".toByteArray()

fun main() {
  var b: Byte

  val ALPH = IntArray(ALPHBET_CNT)

  var min = ALPHBET_CNT - 1
  var max = 0
  var len = 0
  while (r().also { b = it } >= NL) {
    if (b == NL) break
    val i = b - A
    ALPH[i]++
    if (i > max) max = i
    if (i < min) min = i
    len++
  }

  val ans = ByteArray(len) { EMPTY }
  var rmn = len
  var l = 0
  for (i in min..max) {
    val char = (i + A).toByte()
    var cnt = ALPH[i]
    if (cnt % 2 == 1 && ans[len / 2] != EMPTY) break

    while (cnt > 0) {
      if (cnt == 1) {
        ans[len / 2] = char
        rmn--
        cnt--
      } else {
        ans[l] = char
        ans[len - 1 - l++] = char
        rmn -= 2
        cnt -= 2
      }
    }
  }

  O.write(if (rmn == 0) ans else IMPOSSIBLE)
  O.flush()
}
