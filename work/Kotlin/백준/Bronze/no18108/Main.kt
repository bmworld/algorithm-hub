package 백준.Bronze.no18108

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 2
private const val OBS = 1 shl 2
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = BufferedInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0
private const val EOF = -1

private fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

fun main() {
  var v = 0
  var c: Byte
  while (r().also { c = it } in 48..57) v = v * 10 + c - 48
  print(v - 543)
  O.flush()
}
