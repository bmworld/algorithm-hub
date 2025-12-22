package 백준.Bronze.no10798

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 80
private const val OBS = 64
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
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

private const val EMPTY: Byte = 0
fun main() {

  val cMax = 15
  val rMax = 5

  var total = 0
  var lineMax = 0
  val a = Array(rMax) { ByteArray(cMax) }
  var b: Byte = 0
  repeat(rMax) { r ->
    var c = 0
    while (r().also { b = it } > 32) {
      a[r][c++] = b
      total++
    }
    if (c > lineMax) lineMax = c
  }

  val WB = ByteArray(total)
  var WBI = 0
  repeat(lineMax) { c ->
    repeat(rMax) { r ->
      val b = a[r][c]
      if (b != EMPTY) WB[WBI++] = b
    }
  }

  O.write(WB)
  O.flush()
}
