package 백준.Gold.no1074

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 15
private const val OBS = 10
private const val EOF = -1
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0

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
private val WB = ByteArray(WS)
private fun w(
  num: Int,
) {
  var v = num
  var end = WS - 1
  do {
    WB[end--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  val stt = end + 1
  O.write(WB, stt, -stt + WS)
}

private val qr = intArrayOf(0, 0, 1, 1) // 1~4분면
private val qc = intArrayOf(0, 1, 0, 1)

fun main() {
  val n = i()
  val r = i()
  val c = i()

  var cnt = 0
  var sr = 0
  var sc = 0
  var len = 1
  repeat(n) {
    len *= 2
  }

  while (len > 1) {
    val nl = len / 2
    val quadVol = nl * nl
    var qi = 3
    while (qi >= 0) {
      val nsr = sr + nl * qr[qi]
      val nsc = sc + nl * qc[qi]
      if (nsr <= r && nsc <= c) {
        sr = nsr
        sc = nsc
        break
      }
      qi--
    }
    cnt += quadVol * qi
    len = nl
  }

  w(cnt)
  O.flush()
}
