package 백준.Silver.no1735

import java.io.BufferedInputStream

const val IBS = 1 shl 5
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
  val a1 = i()
  val b1 = i()
  val a2 = i()
  val b2 = i()

  val a = a1 * b2 + a2 * b1
  val b = b1 * b2
  val gcd = getGCD(a, b)
  println(a / gcd)
  print(b / gcd)
}

tailrec fun getGCD(a: Int, b: Int): Int = if (b == 0) a else getGCD(b, a % b)
