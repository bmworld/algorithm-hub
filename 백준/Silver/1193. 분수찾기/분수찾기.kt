import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 10
private const val OBS = 1 shl 10
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
  O.write(WB, ++pos, WS - pos)
}


fun main() {
  val n = i()
  val r = searchRound(n)
  val end = r * (r + 1) shr 1
  val stt = end - r + 1
  val o = n - stt + 1
  val ro = r - o + 1
  
  if (r % 2 == 0) {
    w(o)
    O.write(47)
    w(ro)
  } else {
    w(ro)
    O.write(47)
    w(o)
  }
  O.flush()
}

private fun searchRound(
  n: Int,
): Int {
  if (n == 1) return 1
  var round = 0
  var l = 1
  var r = 4472

  while (l <= r) {
    val m = (l + r) shr 1
    val end = m * (m + 1) shr 1
    val stt = end - m + 1
    when {
      n > end -> l = m + 1
      n < stt -> r = m - 1

      else -> {
        round = m
        break
      }
    }
  }
  return round
}