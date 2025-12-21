package 백준.Gold.no1916

import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.util.*

private const val IBS = 100_000
private const val OBS = 1_000
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


private const val WS = 10
private val WB = ByteArray(WS + 1).also { it[WS] = 10 }
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
  pos++
  O.write(WB, pos, WS - pos + 1)
}

private const val INF = Int.MAX_VALUE
private const val SEP = 10_000

fun main() {
  val n = i()
  val m = i()

  val g = Array(n + 1) { mutableListOf<Int>() }
  val cost = Array(n + 1) { i -> Array(n + 1) { j -> if (i == j) 0 else INF } }

  repeat(m) {
    val fr = i()
    val to = i()
    val c = i()
    g[fr].add(to)
    if (cost[fr][to] > c) cost[fr][to] = c
  }

  val fr = i()
  val to = i()
  val q = PriorityQueue<Int>(n)
  q.add(fr)
  while (q.isNotEmpty()) {
    val e = q.poll()
    val acc = e / SEP
    val f = e % SEP
    val cities = g[f]
    repeat(cities.size) {
      val t = cities[it]
      if (t == fr) return@repeat
      val c = cost[f][t]
      val nc = c + acc
      if (fr != f && cost[fr][t] <= nc) return@repeat
      cost[fr][t] = nc
      q.add(nc * SEP + t)
    }
  }
  w(cost[fr][to])
  O.flush()
}

// println("--- cost[$fr][$t] = ${cost[fr][t]} vs cost[$f][$t] = $nc  ---> ${fr != f && cost[fr][t] <= nc}")

//      println("--- cost[$f][$t]: $nc  ->  ${cost[fr][t]} ? ")
//for (f in 1..n) for (t in 1..n) {
//  println(">>>> cost[$f][$t]: ${cost[f][t]}")
//}
