package 백준.Bronze.no1032

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 15
private const val OBS = 1 shl 6
private const val EOF = -1
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0

private fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

private fun i(): Int {
  var v = 0
  var c: Byte
  while (r().also { c = it } in 48..57) v = v * 10 + (c - 48)
  return v
}

private const val MAX_LEN = 50
private const val QUESTION = 63.toByte()

fun main() {
  val n = i()
  val a = ByteArray(MAX_LEN)
  var len = 0
  repeat(n) {
    var c = r()
    var i = 0
    while (c >= 46) {
      val v = a[i]
      when {
        v == 0.toByte() -> {
          a[i] = c
          len++
        }
        v != c && v != QUESTION -> a[i] = QUESTION
      }
      c = r()
      i++
    }
  }

  O.write(a, 0, len)
  O.flush()
}
