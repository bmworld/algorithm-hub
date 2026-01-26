package 백준.Gold.no25908

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 5
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

const val ODD_W = -1
const val EVEN_W = 1

fun main() {
  val m = i()
  val M = i()
  w(getSum(M) - getSum(m - 1))
  O.flush()
}

private fun getSum(
  max: Int
): Int {
  var acc = 0
  var fr = 1
  while (fr <= max) {
    val cnt = max / fr
    var to = max / cnt
    val distinctDvsrCnt = to - fr + 1
    acc += when {
      distinctDvsrCnt % 2 == 0 -> 0
      else -> cnt * if (fr % 2 == 0) EVEN_W else ODD_W
    }
    fr = to + 1
  }
  return acc
}

// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
// ---------------------------------------------------------------------

fun test(
  stt: Int,
  end: Int
) {
  var total = 0
  repeat(end - stt + 1) { i ->
    val v = stt + i
    val w = getW(v)
    total += w
  }
  println("[TEST] $total")
}

fun getW(
  num: Int
): Int {
  if (num == 1) return ODD_W

  var v = num
  var ePow = 0
  var oCnt = 1

  var dvsr = 2
  while (v > 1 && dvsr <= v) {
    var cnt = 0
    while (v % dvsr == 0) {
      v /= dvsr
      cnt++
    }

    if (cnt > 0) {
      if (dvsr % 2 == 0) ePow += cnt
      else oCnt *= cnt + 1
    }
    dvsr++
  }

  var eCnt = maxOf(1, oCnt) * ePow
  return EVEN_W * eCnt + ODD_W * oCnt
}

// 점검 (짝수, 홀수):
// 496584 (8*27*121*19): 72, 24
// 62073 (27*121*19): 0, 24
