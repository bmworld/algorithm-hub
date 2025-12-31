package 백준.Silver.no10815

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 60_000
private const val OBS = 20_000
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

fun main() {

  val N = i()
  val a = IntArray(N) { i() }
  a.sort()

  fun bs(
    t: Int,
  ): Boolean {
    var l = 0
    var r = N - 1
    while (l <= r) {
      val m = (l + r) shr 1
      val mv = a[m]
      when {
        mv > t -> r = m - 1
        mv < t -> l = m + 1
        else -> return true
      }
    }
    return false
  }

  repeat(i()) {
    w(if (bs(i())) 1 else 0)
  }

  O.flush()
}
