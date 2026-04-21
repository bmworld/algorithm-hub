package 백준.Bronze.no10984

import java.io.BufferedInputStream

const val IBS = 1 shl 10
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
  var b: Byte
  while (r().also { b = it } in NUM) v = v * 10 + b - 48
  return v
}

const val POINT: Byte = 46
const val ZERO: Byte = 48

fun main() {
  var b: Byte
  repeat(i()) {
    val N = i()
    var tc = 0
    var tg = 0
    repeat(N) {
      val c = i()

      var g = 0
      var delta = 100
      while (r().also { b = it } >= POINT) {
        if (b in NUM) {
          g += (b - ZERO) * delta
          delta /= 10
        }
      }

      tc += c
      tg += c * g
    }

    val GPAx10 = (tg / tc + 5) / 10
    println("$tc ${GPAx10.toDouble() / 10}")
  }
}

/**
IN
2
1
3 4.0
2
6 0.7
6 1.3


OUT
3 4.0
12 1.0

 */
