package 백준.Silver.no17413

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 16
const val OBS = 1 shl 16
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

fun main() {
  val EOL: Byte = 10
  val SPACE: Byte = 32
  val OPEN: Byte = 60
  val CLSE: Byte = 62
  val MAX_LEN = 100_001

  val S = ByteArray(MAX_LEN)
  var si = 0

  val tmp = ByteArray(MAX_LEN)
  var ti = 0

  fun appendReversed() {
    repeat(ti) {
      S[si++] = tmp[ti - (it + 1)]
    }
    ti = 0
  }

  var b: Byte
  var useFlip = true
  while (r().also { b = it } >= EOL) {
    when {
      b == OPEN -> {
        appendReversed()
        useFlip = false
        S[si++] = b
      }
      b == CLSE -> {
        useFlip = true
        S[si++] = b
      }
      useFlip -> {
        if (b == EOL || b == SPACE) {
          appendReversed()
          S[si++] = b
        } else tmp[ti++] = b
      }
      else -> S[si++] = b
    }
  }

  O.write(S, 0, si)
  O.flush()
}
