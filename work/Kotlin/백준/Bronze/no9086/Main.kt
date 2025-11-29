package 백준.Bronze.no9086

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 14
private const val OBS = 1 shl 1
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


private const val WS = 2
private val WB = ByteArray(WS)

fun main() {
  repeat(i()) {
    var c = r()
    var i = 0
    while (c >= 65) {
      if (i++ == 0) WB[0] = c
      val nc = r()
      when {
        nc >= 65 -> c = nc
        nc in 10..32 -> break
      }
    }
    WB[1] = if (--i == 0) WB[0] else c
    O.write(WB, 0, 2)
    O.write('\n'.code)
  }
  O.flush()
}
