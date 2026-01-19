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
  O.write(WB, ++pos, WS - pos + 1)
}

private const val EMPTY = Int.MIN_VALUE
private const val EVEN_W = +1
private const val ODD_W = -1
private const val PRIME_W = -2
fun main() {
  val S = i()
  val T = i()
  if (S == 1 && T == 1) {
    w(-1)
    O.flush()
    return
  }

  val a = IntArray(T + 1) { EMPTY }
  a[1] = ODD_W

  var total = if (S <= 1) a[1] else 0

  repeat(T - 1) { i ->
    val v = i + 2
    if (a[v] != EMPTY) return@repeat
    val r = when {
      v % 2 == 0 -> a[v / 2] + EVEN_W
      v % 3 == 0 -> a[v / 3] + ODD_W
      v % 5 == 0 -> a[v / 5] + ODD_W
      v % 7 == 0 -> a[v / 7] + ODD_W
      else -> {
        var w = PRIME_W
        val l = v.toLong()
        var prmPow = l * l
        while (prmPow <= T) {
          a[prmPow.toInt()] = --w
          prmPow *= v
        }

        PRIME_W
      }
    }
    a[v] = r.also { if (v >= S) total += it }
  }

  w(total)
  O.flush()
}
