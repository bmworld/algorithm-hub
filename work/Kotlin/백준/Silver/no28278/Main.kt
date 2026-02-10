package 백준.Silver.no28278

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 20
const val OBS = 1 shl 20
val O = BufferedOutputStream(System.`out`, OBS)
val I = BufferedInputStream(System.`in`)
val IB = ByteArray(IBS)
var Ii = 0
var Il = 0
const val EOF = -1

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

fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

const val WS = 10
val WB = ByteArray(WS + 1).also { it[WS] = 10 }
fun w(
  num: Int
) {
  var v = if (num >= 0) num
  else {
    O.write(45)
    -num
  }
  var pos = WS - 1
  do {
    WB[pos--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  O.write(WB, ++pos, WS - pos + 1)
}


const val PUSH = 1
const val POP = 2
const val SIZE = 3
const val EMPTY = 4
const val TOP = 5

fun main() {
  val N = i()
  val a = IntArray(N)
  var len = 0

  repeat(N) {
    val op = i()
    when (op) {
      PUSH -> a[len++] = i()
      POP -> w(if (len > 0) a[--len] else -1)
      SIZE -> w(len)
      EMPTY -> w(if (len == 0) 1 else 0)
      TOP -> w(if (len > 0) a[len - 1] else -1)
    }
  }
  O.flush()
}
