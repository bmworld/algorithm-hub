package 백준.Silver.no1024

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 5
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

private const val MAX_NUM = 1_000_000_000
fun main() {
  val N = i()
  var LEN = i()
  if (N % 2 == 0 && LEN == 2) LEN++

  when {
    getMinSum(LEN) > N -> w(-1)
    else -> {
      var sum: Int
      while (true) {
        val m = N / LEN
        val half = LEN shr 1
        val l = m - half + if (LEN % 2 == 0) 1 else 0
        val r = m + half
        sum = getRangeSum(l, r)
        when {
          sum == N -> {
            repeat(r - l + 1) {
              w(l + it)
            }
            break
          }
          getMinSum(LEN + 1) <= N -> LEN++
          else -> {
            w(-1)
            break
          }
        }
      }
    }
  }

  O.flush()
}

private fun getMinSum(
  len: Int
) = (len - 1) * len shr 1

private fun getRangeSum(
  fr: Int,
  to: Int
) = getSeqSum(minOf(to, MAX_NUM)) - getSeqSum(maxOf(fr - 1, 0))

private fun getSeqSum(v: Int): Int {
  val l = v.toLong()
  return (l * (l + 1) shr 1).toInt()
}

//        println("[L=$len] $l ~ $r ---- $sum  (mSum=${getMinSum(len + 1)})")
