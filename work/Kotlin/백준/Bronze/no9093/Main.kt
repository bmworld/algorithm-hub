package 백준.Bronze.no9093

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1_000
const val OBS = 1_000
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
  var c: Byte
  while (r().also { c = it } in NUM || c == 45.toByte()) {
    when (c) {
      in NUM -> v = v * 10 + c - 48
      else -> s = -1
    }
  }
  return s * v
}

const val MAX_LEN = 20
const val NL: Byte = 10
const val SPACE: Byte = 32
fun main() {
  i()
  var b: Byte
  val SIZE = MAX_LEN + 1
  val word = ByteArray(SIZE)
  val end = MAX_LEN - 1
  var i = end

  while (r().also { b = it } >= NL) {
    when (b) {
      NL -> {
        word[MAX_LEN] = NL
        O.write(word, ++i, SIZE - i)
        i = end
      }
      SPACE -> {
        word[MAX_LEN] = SPACE
        O.write(word, ++i, SIZE - i)
        i = end
      }
      else -> word[i--] = b
    }
  }

  O.flush()
}
