package 백준.Bronze.no10987

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 100
const val OBS = 3
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

const val WS = 10
val WB = ByteArray(WS)
fun w(
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
  var b: Int
  var cnt = 0
  while (r().also { b = it.toInt() } >= 10) {
    if (b == 10) break
    if (b == 97 || b == 101 || b == 105 || b == 111 || b == 117) cnt++
  }

  w(cnt)
  O.flush()
}
