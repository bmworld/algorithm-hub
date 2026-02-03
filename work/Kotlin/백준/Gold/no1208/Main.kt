package 백준.Gold.no1208

import java.io.BufferedOutputStream
import java.io.DataInputStream

const val IBS = 1 shl 12
const val OBS = 1 shl 4
val O = BufferedOutputStream(System.`out`, OBS)
val I = DataInputStream(System.`in`)
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
  val HALF = N shr 1
  var MAX_V = 0
  val a = IntArray(N) {
    i().also {
      val v = abs(it)
      if (v > MAX_V) MAX_V = v
    }
  }

  val ls = generateSums(a, 0, HALF)
  val HALF_V = MAX_V * N
  val rSCnt = generateSumCnts(a, HALF + 1, N - 1, (HALF_V shl 1) + 1, HALF_V)

  var cnt = 0L
  repeat(ls.size) { li ->
    cnt += rSCnt[HALF_V + S - ls[li]]
  }

  w(cnt + if (S == 0) -1 else 0)
  O.flush()
}

fun generateSums(arr: IntArray, stt: Int, end: Int): IntArray {
  val sums = IntArray(1 shl (end - stt + 1))
  var seq = 0
  sums[seq++] = 0
  var i = stt
  while (i <= end) {
    val v = arr[i++]
    repeat(seq) { j ->
      sums[seq + j] = sums[j] + v
    }
    seq = seq shl 1
  }

  return sums
}

fun generateSumCnts(arr: IntArray, stt: Int, end: Int, size: Int, HALF_V: Int): LongArray {
  val sums = IntArray(1 shl (end - stt + 1))
  val sumCnt = LongArray(size)
  sumCnt[HALF_V] = 1
  var seq = 0
  sums[seq++] = 0
  var i = stt
  while (i <= end) {
    val v = arr[i++]
    repeat(seq) { j ->
      val sum = sums[j] + v
      sums[seq + j] = sum
      sumCnt[HALF_V + sum]++
    }
    seq = seq shl 1
  }

  return sumCnt
}

fun abs(v: Int): Int = if (v > 0) v else -v

// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
fun printArr(sums: LongArray) {
  repeat(sums.size) {
    println("--sums[$it] = ${sums[it]}")
  }
}

/*
IN
40 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0

OUT
1099511627775

IN
20 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0


 */

//println("a[$i] = ${a[i]}, nxt=$nxt ---> ${nxt + HALF}")
//println("[$i] $sum1 (c1=${cnt1}) ------- $sum2 (c2=$cnt2)")
//println("$li sum= $sum1 ---> $sum2(${rSCnt[sum2]})")
