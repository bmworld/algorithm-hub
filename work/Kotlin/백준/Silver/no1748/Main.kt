package 백준.Silver.no1748

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 9
const val OBS = 9
val O = BufferedOutputStream(System.out, OBS)
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
  num: Int
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

val NUM = 48..57
fun main() {
  var v = 0
  var b: Byte
  var digits = 0
  while (r().also { b = it } in NUM) {
    v = v * 10 + b - 48
    digits++
  }

  var base = 1
  var ans = 0
  repeat(digits - 1) {
    ans += (it + 1) * base * 9
    base *= 10
  }
  ans += digits * (v - base + 1)

  w(ans)
  O.flush()
}
