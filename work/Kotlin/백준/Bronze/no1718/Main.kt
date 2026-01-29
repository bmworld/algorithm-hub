package 백준.Bronze.no1718

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 15
const val OBS = 1 shl 15
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

fun char(buf: ByteArray): Int {
  var len = 0
  var c: Byte
  while (r().also { c = it } >= 10.toByte()) {
    if (c == 10.toByte()) break
    buf[len++] = c
  }
  return len
}

const val a = 97
const val MAX_LEN = 30_000
const val ALPH_SIZE = 26

fun main() {
  val txt = ByteArray(MAX_LEN)
  val txtLen = char(txt)
  val key = ByteArray(MAX_LEN)
  val keyLen = char(key)

  var keyPos = 0
  repeat(txtLen) {
    val char = txt[it]
    val i = keyPos++.also { if (it + 1 == keyLen) keyPos = 0 }
    if (char == 32.toByte()) return@repeat
    var encrypted = char - (key[i] - a + 1)
    if (encrypted < a) encrypted += ALPH_SIZE
    txt[it] = (encrypted).toByte()
  }

  O.write(txt, 0, txtLen)
  O.flush()
}
