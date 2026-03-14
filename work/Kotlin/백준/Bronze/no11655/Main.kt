package 백준.Bronze.no11655

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 7
const val OBS = 1 shl 7
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

const val ROT = 13
const val ALPA_LEN = 26
const val MAX_LEN = 100
const val SPACE: Byte = 32
const val a: Byte = 65
const val z: Byte = 90
const val A: Byte = 97
const val Z: Byte = 122

fun main() {
  var b: Byte
  val words = ByteArray(MAX_LEN)
  var i = 0
  while (r().also { b = it } >= SPACE) {
    words[i++] = when (b) {
      in a..z -> {
        var nb = b + ROT
        if (nb > z) nb -= ALPA_LEN
        nb.toByte()
      }
      in A..Z -> {
        var nb = b + ROT
        if (nb > Z) nb -= ALPA_LEN
        nb.toByte()
      }
      else -> b
    }
  }
  O.write(words, 0, i)
  O.flush()
}
