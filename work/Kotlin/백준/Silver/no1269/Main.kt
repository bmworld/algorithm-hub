package 백준.Silver.no1269

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 15
const val OBS = 1 shl 4
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

const val WS = 10
val WB = ByteArray(WS)
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
  O.write(WB, ++pos, WS - pos)
}

fun main() {
  val A = i()
  val B = i()

  val map = HashMap<Int, Int>()
  repeat(A) {
    val v = i()
    map[v] = (map[v] ?: 0) + 1
  }

  var total = A + B
  repeat(B) {
    val v = i()
    val cnt = map[v]
    when (cnt) {
      null -> map[v] = 1
      else -> {
        total -= cnt + 1
        map[v] = 0
      }
    }
  }

  w(total)
  O.flush()
}
