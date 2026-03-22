package 백준.Bronze.no1075

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 4
const val OBS = 1 shl 1
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


fun main() {
  var N = i()
  val F = i()

  if (100 % F == 0) {
    O.write(48)
    O.write(48)
  } else {

    N /= 100
    N *= 100

    var v = 0
    while (v < 100) {
      if ((N + v) % F == 0) break
      v++
    }

    val NUM = ByteArray(2)
    var k = 10
    repeat(2) {
      NUM[it] = (v / k + 48).toByte()
      v %= k
      k /= 10
    }
    O.write(NUM)
  }

  O.flush()
}

/**
IN
946715769
81
OUT
69
 */
