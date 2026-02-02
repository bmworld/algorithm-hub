package 백준.Gold.no1208

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 12
private const val OBS = 1 shl 4
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

private const val WS = 20
private val WB = ByteArray(WS)
private fun w(
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
  var MAX = 0
  val a = IntArray(N) {
    i().also {
      val v = abs(it)
      if (v > MAX) MAX = v
    }
  }
  val HALF = MAX * N
  val lSum = LongArray(HALF * 2 + 1)
  val rSum = LongArray(HALF * 2 + 1)
  lSum[HALF] = 1
  rSum[HALF] = 1

  var lSumMin = Int.MAX_VALUE
  var lSumMax = Int.MIN_VALUE
  fun dfs(
    l: Int,
    r: Int,
    acc: Int,
    cnter: LongArray,
  ) {
    repeat(r - l + 1) {
      val i = l + it
      val v = a[i]
      val nxt = v + acc
      cnter[HALF + nxt]++
      if (cnter == lSum) {
        if (lSumMax < nxt) lSumMax = nxt
        if (lSumMin > nxt) lSumMin = nxt
      }

      dfs(i + 1, r, nxt, cnter)
    }
  }

  val m = N / 2
  dfs(0, m, 0, lSum)
  dfs(m + 1, N - 1, 0, rSum)

  var cnt = 0L
  var usedZero = false
  repeat(lSumMax - lSumMin + 1) { i ->
    var sum1 = lSumMin + i
    if (sum1 == 0) usedZero = true
    val cnt1 = lSum[HALF + sum1]
    if (cnt1 == 0L) return@repeat
    cnt += cnt1 * rSum[HALF + S - sum1]
  }
  if (!usedZero) {
    cnt += lSum[HALF] * rSum[HALF + S]
  }

  w(cnt + if (S == 0) -1 else 0)
  O.flush()
}

fun abs(v: Int): Int = if (v > 0) v else -v

/*
IN
40 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0

OUT
1099511627775
 */

//println("a[$i] = ${a[i]}, nxt=$nxt ---> ${nxt + HALF}")
//println("[$i] $sum1 (c1=${cnt1}) ------- $sum2 (c2=$cnt2)")
