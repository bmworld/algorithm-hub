package 백준.Bronze.no2581

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 12
private const val OBS = 10
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

private val WB = ByteArray(WS + 1).also { it[WS] = 10 }
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

fun main() {

  val M = i()
  val N = i()
  val ch = BooleanArray(N + 1).also {
    it[0] = true
    it[1] = true
  }

  val total = (N * (N + 1) - (M - 1) * M) / 2
  var sumOfNonPrimes = if (M <= 1) 1 else 0
  var v = 2
  while (v * 2 <= N) {
    for (d in v * 2..N step v) {
      if (ch[d]) continue
      ch[d] = true
      if (d >= M) sumOfNonPrimes += d
    }
    v++
  }

  val sum = total - sumOfNonPrimes
  when {
    sum == 0 -> w(-1)
    else -> {
      w(sum)
      for (v in M..N) {
        if (ch[v]) continue
        w(v)
        break
      }
    }
  }

  O.flush()
}
