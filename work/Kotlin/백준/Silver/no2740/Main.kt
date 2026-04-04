package 백준.Silver.no2740

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 14
const val OBS = 1 shl 14
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
val WB = ByteArray(WS + 1)
fun w(
  num: Int,
  end: Boolean = false
) {
  WB[WS] = if (end) 10 else 32
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

fun main() {
  val N = i()
  val M = i()

  val a = IntArray(N * M) { i() }

  val M2 = i()
  val K = i()

  val b = IntArray(M * K) { i() }

  repeat(N) { n ->
    repeat(K) { k ->
      var sum = 0
      repeat(M) { m ->
        sum += a[pos(n, m, M)] * b[pos(m, k, K)]
      }
      w(sum, k + 1 == K)
    }
  }
  O.flush()
}

fun pos(r: Int, c: Int, CAP: Int): Int = r * CAP + c
