package 백준.Silver.no9625

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 2
const val OBS = 1 shl 7
val I = BufferedInputStream(System.`in`)
val O = BufferedOutputStream(System.`out`, OBS)
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

const val ZERO: Byte = 48
val NUM = ZERO..ZERO + 9
fun i(): Int {
  var v = 0
  var b: Byte
  while (r().also { b = it } in NUM) v = v * 10 + b - ZERO
  return v
}


const val WS = 10
val WB = ByteArray(WS + 1).also { it[WS] = 32 }
fun w(
  num: Int,
) {
  var x = num
  var pos = WS - 1
  do {
    WB[pos--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  O.write(WB, ++pos, WS - pos + 1)
}


fun main() {
  val K = i()
  var a = 0
  var b = 1
  if (K > 1) {
    val arr = IntArray(K + 1).also {
      it[1] = 1
      for (i in 2..K) it[i] = it[i - 2] + it[i - 1]
    }
    a = arr[K - 1]
    b = arr[K]
  }

  w(a)
  w(b)
  O.flush()
}
