package 백준.Silver.no1021

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 8
private const val OBS = 1 shl 4
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
  num: Long
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

private const val L = 1L
private const val LONG_BIT_CNT = 64
fun main() {
  val N = i()
  val M = i()
  var q = (L shl N) - 1

  var cnt = 0L
  var fr = 0
  repeat(M) {
    val to = i() - 1
    cnt += op(q, fr, to)
    fr = move(q, to)
    q = pop(q, to)
  }

  w(cnt)
  O.flush()
}

private fun pop(
  q: Long,
  t: Int
) = q xor (L shl t)

private fun op(
  q: Long,
  fr: Int,
  to: Int
): Int {
  if (fr == to) return 0
  val f = if (fr <= to) fr else to
  val t = if (fr == f) to else fr

  val mask = ((L shl (t - f)) - 1) shl (f + 1)
  val l = (q and mask).countOneBits()
  val r = q.countOneBits() - l
  return minOf(l, r)
}

private fun move(
  q: Long,
  pos: Int
): Int {

  val top = LONG_BIT_CNT - q.countLeadingZeroBits() - 1
  val preMask = (L shl pos) - 1
  val postMask = ((L shl (top - pos)) - 1) shl (pos + 1)
  val end = (L shl pos).countLeadingZeroBits() == q.countLeadingZeroBits()

  val candidates = q and if (end) preMask else postMask
  val next = candidates.countTrailingZeroBits()
  return if (next == LONG_BIT_CNT) candidates.countTrailingZeroBits() else next
}

//println("[op] l=$l, r=$r")
//println(">>>> ${q.toString(2)}] ${fr + 1} -> ${to + 1}")
//println("<< ${q.toString(2)}] next = ${fr + 1}")
