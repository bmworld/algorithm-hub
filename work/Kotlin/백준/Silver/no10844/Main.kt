package 백준.Silver.no10844

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 3
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

const val NUM_LEN = 10
const val MOD = 1_000_000_000
fun main() {
  val len = i()
  var num = IntArray(NUM_LEN) { if (it == 0) 0 else 1 }
  val tmp = IntArray(NUM_LEN)

  var i = 1
  while (i < len) {
    repeat(NUM_LEN) { tmp[it] = 0 }

    repeat(NUM_LEN) {
      val v = num[it]
      val l = it - 1
      val r = it + 1
      if (it < 9) {
        val rv = tmp[r] + v
        tmp[r] = if (rv >= MOD) rv - MOD else rv
      }

      if (it > 0) {
        val lv = tmp[l] + v
        tmp[l] = if (lv >= MOD) lv - MOD else lv
      }
    }

    num = tmp.copyOf()
    i++
  }

  var cnt = 0
  repeat(NUM_LEN) {
    val nc = cnt + num[it]
    cnt = if (nc >= MOD) nc - MOD else nc
  }
  w(cnt)
  O.flush()
}
