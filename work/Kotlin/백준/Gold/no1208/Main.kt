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


var MAX_V = 100_000
var MAX_CNT = 40
var OFFSET = MAX_V * MAX_CNT

fun main() {
  val N = i()
  val S = i()
  val a = IntArray(N) { i() }
  val HALF = (N shr 1) + if (N % 2 == 0) -1 else 0
  val rSumCnt = calcSumCnts(a, HALF + 1, N - 1)
  w(getCnt(HALF, S, rSumCnt, a) + if (S == 0) -1 else 0)
  O.flush()
}

fun getCnt(HALF: Int, Goal: Int,
  rightSumCounts: LongArray, arr: IntArray): Long {
  var cnt = 0L

  val rmnByZero = OFFSET + Goal - 0
  cnt += rightSumCounts[rmnByZero]

  var seq = 1
  val leftSum = IntArray(1 shl (HALF + 1))
  repeat(HALF + 1) { i ->
    val v = arr[i]
    repeat(seq) { j ->
      val sum = leftSum[j] + v
      leftSum[seq + j] = sum
      val rmn = OFFSET + Goal - sum
      cnt += rightSumCounts[rmn]
    }
    seq = seq shl 1
  }
  return cnt
}

fun calcSumCnts(arr: IntArray, stt: Int, end: Int): LongArray {
  val sums = IntArray(1 shl (end - stt + 1))
  val cnts = LongArray((OFFSET shl 1) + 1).also {
    it[OFFSET] = 1
  }

  var seq = 1
  repeat(end - stt + 1) {
    val i = stt + it
    val v = arr[i]
    repeat(seq) { j ->
      val sum = sums[j] + v
      sums[seq + j] = sum
      cnts[OFFSET + sum]++
    }
    seq = seq shl 1
  }
  return cnts
}

// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
//fun quickSort(
//  a: IntArray,
//  l: Int,
//  r: Int,
//) {
//  if (l >= r) return
//  val (pl, pr) = threeWayPartition(a, l, r)
//  quickSort(a, l, pl - 1)
//  quickSort(a, pr + 1, r)
//}
//
//fun threeWayPartition(
//  a: IntArray,
//  l: Int,
//  r: Int
//): Pair<Int, Int> {
//  var pos = l
//  var pl = l
//  var pr = r
//  val piv = a[(l + r) shr 1]
//
//  while (pos <= pr) {
//    val v = a[pos]
//    when {
//      v < piv -> {
//        swap(a, pos, pl)
//        pl++
//        pos++
//      }
//
//      v > piv -> {
//        swap(a, pos, pr)
//        pr--
//      }
//
//      else -> pos++
//    }
//  }
//  return Pair(pl, pr)
//}
//
//fun swap(
//  a: IntArray,
//  i: Int,
//  j: Int,
//) {
//  val tmp = a[i]
//  a[i] = a[j]
//  a[j] = tmp
//}

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
//        println(">>> $l (<$L), $r(<$R) -> $cnt")
//    println("---- $l (<$L), $r(<$R) = $lSum + $rSum == $sum")
