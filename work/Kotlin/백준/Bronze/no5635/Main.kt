package 백준.Bronze.no5635

import java.io.BufferedInputStream

const val IBS = 2_700
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

const val ZERO: Byte = 48
val NUM = ZERO..ZERO + 9
fun i(): Int {
  var v = 0
  var b: Byte
  while (r().also { b = it } in NUM) v = v * 10 + b - ZERO
  return v
}

val reader = StringBuilder(15)
fun s(): String {
  var b: Byte
  while (r().also { b = it } >= 65) reader.append(b.toInt().toChar())
  val r = reader.toString()
  reader.setLength(0)
  return r
}

const val DD_SEP = 100
const val MM_SEP = DD_SEP * 100

fun main() {
  val cnds = i()

  fun encode(yyyy: Int, mm: Int, dd: Int): Int = yyyy * MM_SEP + mm * DD_SEP + dd

  val name = s()
  val dd = i()
  val mm = i()
  val yyyy = i()

  val v = encode(yyyy, mm, dd)
  var min = v
  var ordest = name
  var max = v
  var youngest = name
  repeat(cnds - 1) {
    val name = s()
    val dd = i()
    val mm = i()
    val yyyy = i()
    val v = encode(yyyy, mm, dd)
    if (v < min) {
      min = v
      ordest = name
    }
    if (v > max) {
      max = v
      youngest = name
    }
  }

  println(youngest)
  print(ordest)
}

//    println("$name $v vs $min / $max")
