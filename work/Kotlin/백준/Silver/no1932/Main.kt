package 백준.Silver.no1932

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 3_000
private const val OBS = 10
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
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
private val WB = ByteArray(WS)
private fun w(
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
  pos++
  O.write(WB, pos, WS - pos)
}

private const val SEP = 10_000
fun main() {
  val n = i()
  val a = Array(n) { mutableListOf<Long>() }


  repeat(n) { i ->
    var v = 0L
    var c: Byte
    while (r().also { c = it } in NUM || c == 32.toByte() || c == 10.toByte()) {
      when (c) {
        in NUM -> v = v * 10 + c - 48

        else -> {
          a[i] += v * SEP + v
          v = 0
          if (c == 10.toByte()) return@repeat
        }
      }
    }
  }

  var max = 0
  for (i in 0 until n) {
    val isGround = i + 1 == n
    for (j in 0 until a[i].size) {
      val p = a[i][j]
      val pAcc = (p / SEP).toInt()
      if (isGround) {
        if (pAcc > max) max = pAcc
      } else {
        val l = a[i + 1][j]
        val lAcc = l / SEP
        val lv = l % SEP
        val nlAcc = pAcc + lv
        if (nlAcc > lAcc) a[i + 1][j] = nlAcc * SEP + lv

        val r = a[i + 1][j + 1]
        val rAcc = r / SEP
        val rv = r % SEP
        val nrAcc = pAcc + rv
        if (nrAcc > rAcc) a[i + 1][j + 1] = nrAcc * SEP + rv
      }
    }
  }
  w(max)
  O.flush()
}
