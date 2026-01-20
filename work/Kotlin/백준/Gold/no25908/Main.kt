package 백준.Gold.no25908

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 5
private const val OBS = 1 shl 5
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = BufferedInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0
private const val EOF = -1

private fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

private val NUM = 48..57
private fun i(): Int {
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

private const val WS = 10

private val WB = ByteArray(WS + 1).also { it[WS] = 32 }
private fun w(
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
  O.write(WB, ++pos, WS - pos + 1)
}

private const val EVEN_W = 1
private const val ODD_W = -1

fun main() {
  val stt = i()
  val end = i()
  var total = 0
  repeat(end - stt + 1) { i ->
    total += getW(stt + i)
  }

  w(total)
  O.flush()
}

private fun getW(num: Int): Int {
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
  val w = EVEN_W * eCnt + ODD_W * oCnt
  return w
}

//println("---- $v -> even=$eCnt ($ew), odd= $oCnt ($ow)")
//println("dvsr=$dvsr ($cnt)")
//println("num = ${num}, $w")

// 점검 (짝수, 홀수):
// 9: 0,3
// 11: 0,2
// 99: 0,6
// 496584 (8*27*121*19): 72, 24
// 62073 (27*121*19): 0, 24
