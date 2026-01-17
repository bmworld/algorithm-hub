package 백준.Silver.no1446

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 9
private const val OBS = 1 shl 5
private val O = BufferedOutputStream(System.out, OBS)
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

private const val DIST_SEP = 100_000UL
private const val POS_SEP = 10_000_000_000UL
private const val EMPTY = 0UL

@OptIn(ExperimentalUnsignedTypes::class)
fun main() {
  val N = i()
  val D = i()
  val r = ULongArray(N)
  val dists = IntArray(D + 1) { it }

  fun getRoad(
    roads: ULongArray,
    ri: Int
  ): ULong = roads[ri]

  var len = 0
  repeat(N) {
    val fr = i()
    val to = i()
    val dist = i()
    if (to > D || to - fr <= dist) return@repeat
    var i = len
    while (i > 0) {
      val prv = getRoad(r, i - 1)
      val prvFr = (prv / POS_SEP).toInt()
      val td = prv % POS_SEP
      val prvTo = (td / DIST_SEP).toInt()
      val prvD = (td % DIST_SEP).toInt()
      if (fr < prvFr) r[i--] = prv
      else if (fr == prvFr && to == prvTo && dist >= prvD) return@repeat
      else if (fr < prvFr) r[i--] = prv
      else break
    }
    r[i] = fr.toULong() * POS_SEP + to.toULong() * DIST_SEP + dist.toULong()
    len++
  }

  var i = 0
  var road = getRoad(r, i)
  repeat(D) { cur ->
    val nxt = cur + 1
    dists[nxt] = minOf(dists[nxt], dists[cur] + 1)
    if (road == EMPTY || (road / POS_SEP).toInt() != cur) return@repeat

    while (i < len) {
      val fr = (road / POS_SEP).toInt()
      val td = road % POS_SEP
      val to = (td / DIST_SEP).toInt()
      val d = (td % DIST_SEP).toInt()
      if (cur != fr) break
      dists[to] = minOf(dists[to], dists[to - 1] + 1, dists[fr] + d)
      if (i + 1 < len) road = getRoad(r, ++i)
      else break
    }
  }

  w(dists[D])
  O.flush()
}

//     println("------dists[$cur] = ${dists[cur]}")
//     println("-> [r=$i] dists[$to] = ${dists[to - 1] + 1} vs ${dists[fr] + d}")

//for (i in 0 until len) {
//  println("---road = ${r[i]}")
//}

/**
// TEST
1 100
0 100 99
-> 99

// 범위 밖
1 100
0 100 101
-> 101

// 범위 밖
2 100
101 104 54
0 102 50


// 핵심점검: 최소거리 dists[to] 고려 -> minOf("dists[to]", dists[to - 1] + 1, dists[fr] + d))
12 10000
0 9000 500
0 100 100
100 1000 100
100 200 50
1000 2000 100
200 3000 100
200 5000 100
3000 4000 100
4000 5000 100
5000 7000 1000
7000 9000 100
9000 10000 1

// OUT: 501

 */
