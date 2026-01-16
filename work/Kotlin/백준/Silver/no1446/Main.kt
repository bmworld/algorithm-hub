package 백준.Silver.no1446

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 8
private const val OBS = 1 shl 6
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

fun main() {
  val N = i()
  val D = i()

  val roads = Array<Road?>(N + 1) { null }
  roads[0] = Road(0, 0, D)
  val dists = IntArray(D + 1) { it }
  var len = 0
  repeat(N) {
    val fr = i()
    val to = i()
    val dist = i()
    if (to > D || to - fr <= dist) return@repeat
    val cur = Road(fr, to, dist)
    var i = len + 1
    while (i > 0) {
      val prv = roads[i - 1]!!
      val comp = prv.to
      if (to == comp && dist >= prv.w) return@repeat
      else if (to < comp) roads[i--] = prv
      else break
    }
    roads[i] = cur
    len++
  }

  repeat(len) {
    val i = it + 1
    val cur = roads[i]!!
    val prv = roads[i - 1]!!
    val cDist = cur.fr + cur.w
    val pDist = dists[prv.to] + (if (prv.to <= cur.fr) cDist else cur.to) - prv.to
    dists[cur.to] = minOf(pDist, cDist)
    if (i == len) dists[D] = dists[cur.to] + D - cur.to
  }

  w(dists[D])
  O.flush()
}

data class Road(
  val fr: Int,
  val to: Int,
  var w: Int
)

//    println("------------------------------------ $pos ($dist) / $lastDist  / $loopFr")
//println("-----prv=$prv --> $pDist")
//println("-----cur=$cur --> $cDist")
