package 백준.Bronze.no10448

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 8
const val OBS = 1 shl 5
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

const val MAX_T = 44
fun main() {

  val T = i()
  val cnds = IntArray(T)
  var max_x = 0
  repeat(T) {
    val x = i()
    cnds[it] = x
    if (x > max_x) max_x = x
  }
  val eureka = IntArray(max_x + 1)

  IntArray(MAX_T + 1).also {
    for (i in 1 until it.size) it[i] = it[i - 1] + i
    for (i in 1..MAX_T) {
      val x1 = it[i]
      if (x1 >= max_x) break
      for (j in 1..MAX_T) {
        val x2 = it[j]
        val sum1 = x1 + x2
        if (sum1 >= max_x) break
        for (k in 1..MAX_T) {
          val sum2 = sum1 + it[k]
          if (sum2 > max_x) break
          eureka[sum2] = 1
        }
      }
    }
  }

  for (x in cnds)
    w(eureka[x]).also { O.write(10) }

  O.flush()
}
