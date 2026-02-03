package 백준.Gold.no1208

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 12
const val OBS = 1 shl 4
val O = BufferedOutputStream(System.`out`, OBS)
val I = BufferedInputStream(System.`in`)
val IB = ByteArray(IBS)
var Ii = 0
var Il = 0
const val EOF = -1

fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

val NUM = 48..57
fun i(): Int {
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

const val WS = 20
val WB = ByteArray(WS)
fun w(
  num: Long,
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
  val N = i()
  val S = i()
  var MAX_V = 0
  val a = IntArray(N) {
    i().also {
      val v = abs(it)
      if (v > MAX_V) MAX_V = v
    }
  }

  val HALF = (N shr 1) + if (N % 2 == 0) -1 else 0
  val offset = MAX_V * (N - HALF)
  val endRange = (offset shl 1) + 1
  val rSumCnt = calcSumCnts(a, HALF + 1, N - 1, endRange, offset)
  var cnt = getCnt(HALF, offset, S, endRange, rSumCnt, a)
  w(cnt + if (S == 0) -1 else 0)
  O.flush()
}

fun getCnt(HALF: Int, offset: Int, Goal: Int, endRange: Int,
  rightSumCounts: LongArray, arr: IntArray): Long {
  var cnt = 0L

  val rmnByZero = offset + Goal - 0
  if (rmnByZero in 0 until endRange) cnt += rightSumCounts[rmnByZero]

  var seq = 1
  val leftSum = IntArray(1 shl (HALF + 1))
  repeat(HALF + 1) { i ->
    val v = arr[i]
    repeat(seq) { j ->
      val sum = leftSum[j] + v
      leftSum[seq + j] = sum
      val rmn = offset + Goal - sum
      if (rmn in 0 until endRange) cnt += rightSumCounts[rmn]
    }
    seq = seq shl 1
  }
  return cnt
}

fun calcSumCnts(arr: IntArray, stt: Int, end: Int, size: Int, offset: Int): LongArray {
  val sums = IntArray(1 shl (end - stt + 1))
  val cnts = LongArray(size).also {
    it[offset] = 1
  }

  var seq = 1
  repeat(end - stt + 1) {
    val i = stt + it
    val v = arr[i]
    repeat(seq) { j ->
      val sum = sums[j] + v
      sums[seq + j] = sum
      cnts[offset + sum]++
    }
    seq = seq shl 1
  }
  return cnts
}

fun abs(v: Int): Int = if (v > 0) v else -v

/*
IN
40 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
OUT
1099511627775
 */
