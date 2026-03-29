package 백준.Bronze.no3040

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 5
const val OBS = 1 shl 5
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

fun i(): Int {
  var v = 0
  var c: Byte
  while (r().also { c = it } in 48..57) v = v * 10 + (c - 48)
  return v
}


const val WS = 10
val WB = ByteArray(WS + 1).also { it[WS] = 10 }

fun w(num: Int) {
  var x = num
  var pos = WS - 1
  do {
    WB[pos--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  O.write(WB, ++pos, WS - pos + 1)
}

fun main() {
  val GOAL = 100
  var sum = 0

  val TOTAL = 9
  val cnds = IntArray(TOTAL) { i().also { sum += it } }

  loop@ for (i in 0 until TOTAL - 1)
    for (j in i + 1 until TOTAL) {
      if (sum - (cnds[i] + cnds[j]) != GOAL) continue
      repeat(TOTAL) {
        if (it == i || it == j) return@repeat
        w(cnds[it])
      }
      break@loop
    }

  O.flush()
}
