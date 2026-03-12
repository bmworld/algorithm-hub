package 백준.Bronze.no1292

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 2
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
  num: Int,
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
  val fr = i()
  val to = i()

  val n1 = getNum(fr)
  val n2 = getNum(to)

  w(if (n1 == n2) (to - fr + 1) * n2 else {
    var sum = 0
    var n = n1
    while (n <= n2) {
      sum += when (n) {
        n1 -> n * (((n * (n + 1)) shr 1) - (fr - 1))
        n2 -> n * (to - (((n - 1) * n) shr 1))
        else -> n * n
      }
      n++
    }

    sum
  })
  O.flush()
}

fun getNum(pos: Int): Int {
  var l = 1
  var r = 45

  while (l <= r) {
    val m = (l + r) shr 1
    val cur = (m * (m + 1)) shr 1
    val prv = ((m - 1) * m) shr 1

    when {
      pos > prv && pos <= cur -> return m
      pos > cur -> l = m + 1
      else -> r = m - 1
    }
  }

  return -1
}
