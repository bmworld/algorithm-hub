package 백준.Bronze.no1357

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 4
const val OBS = 1 shl 4
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

const val MAX_LEN = 4
const val ZERO: Byte = 48
fun main() {
  var b: Byte
  val NUM = IntArray(MAX_LEN)

  var i = 0
  var lastPos = 0
  while (r().also { b = it } >= ZERO) NUM[i++] = (b - ZERO).also { if (it > 0) lastPos = i }

  i = 0
  while (r().also { b = it } >= ZERO) NUM[i] = NUM[i++] + (b - ZERO).also { if (it > 0 && i > lastPos) lastPos = i }

  var usePrint = false
  repeat(lastPos) { i ->
    var v = NUM[i]

    val overflowed = v >= 10
    if (overflowed) {
      v -= 10
      val ni = (i + 1).also { if (it > lastPos) lastPos = it }
      NUM[ni]++
    }
    if (v > 0) usePrint = true

    if (usePrint) O.write(v + ZERO)
    if (i + 1 == lastPos && overflowed) O.write(1 + ZERO)
  }

  O.flush()
}
