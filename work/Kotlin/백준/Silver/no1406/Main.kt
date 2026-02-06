package 백준.Silver.no1406

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.util.*

const val IBS = 1 shl 16
const val OBS = 1 shl 10
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

val NUM = 48..57
fun i(): Int {
  var v = 0
  var s = 1
  var b: Byte
  while (r().also { b = it } in NUM || b == 45.toByte()) {
    when (b) {
      in NUM -> v = v * 10 + b - 48
      else -> s = -1
    }
  }
  return s * v
}

const val L: Byte = 76
const val D: Byte = 68
const val B: Byte = 66
const val P: Byte = 80
val UPPER_CASE = 65..90
val LOWER_CASE = 97..122
fun getChar(): Byte {
  var b: Byte
  var char: Byte = 0
  while (r().also { b = it } in UPPER_CASE || b in LOWER_CASE) char = b
  return char
}

const val MAX_LEN = 600_000
fun main() {
  val str = LinkedList<Byte>()
  var b: Byte
  while (r().also { b = it } in LOWER_CASE) str.add(b)

  var i = str.size
  repeat(i()) {
    when (getChar()) {
      L -> if (i > 0) i-- else 0
      D -> str.size.also { if (i < it) i++ }
      B -> if (i > 0) str.removeAt((i - 1).also { i = it })
      P -> str.add(i.also { i = it + 1 }, getChar())
    }
  }

  O.write(str.toByteArray())
  O.flush()
}
