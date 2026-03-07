package 백준.Bronze.no5585

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 15
const val OBS = 1 shl 8
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
val WB = ByteArray(WS + 1)
fun w(
  num: Int,
  end: Boolean = false
) {
  WB[WS] = if (end) 10 else 32
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
  O.write(WB, ++pos, WS - pos + 1)
}

const val NL: Byte = 10
const val SPACE: Byte = 32
fun main() {
  var b: Byte
  val CNT = IntArray(4)

  while (r().also { b = it } >= EOF) {
    when (b) {
      in 97..122 -> CNT[0]++
      in 65..90 -> CNT[1]++
      in 48..57 -> CNT[2]++
      SPACE -> CNT[3]++
      NL -> {
        repeat(CNT.size) {
          w(CNT[it], it + 1 == CNT.size)
          CNT[it] = 0
        }
      }
      else -> break
    }
  }
  O.flush()
}
