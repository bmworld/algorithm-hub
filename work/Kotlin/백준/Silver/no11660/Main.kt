package 백준.Silver.no11660

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 120_000
private const val OBS = 1_000
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
private val WB = ByteArray(WS + 1).also { it[WS] = 10 }
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
  O.write(WB, pos, WS - pos + 1)
}

fun main() {
  val n = i()
  val m = i()
  val rowAcc = Array(n + 1) { IntArray(n + 1) }
  val total = Array(n + 1) { IntArray(n + 1) }
  repeat(n) { i ->
    val r = i + 1
    repeat(n) { j ->
      val c = j + 1
      val v = rowAcc[r][c - 1] + i()
      rowAcc[r][c] = v
      total[r][c] = v + total[r - 1][c]

    }
  }

  repeat(m) {
    val r1 = i()
    val c1 = i()
    val r2 = i()
    val c2 = i()
    w(total[r2][c2] - total[r2][c1 - 1] - total[r1 - 1][c2] + total[r1 - 1][c1 - 1])
  }

  O.flush()
}
