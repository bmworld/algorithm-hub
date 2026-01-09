package 백준.Gold.no11779

import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.util.*

private const val IBS = 300_000
private const val OBS = 30_000
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
private val WB = ByteArray(WS + 1)
private fun w(
  num: Int,
  end: Boolean = false,
) {
  WB[WS] = if (end) 10 else 32
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

  val g = Array(n + 1) { HashMap<Int, Int>() }
  val cost = IntArray(n + 1) { INF }
  val trace = IntArray(n + 1)
  repeat(m) {
    val fr = i()
    val to = i()
    val cost = i()

    val buses = g[fr]
    val prev = buses[to] ?: INF
    if (prev > cost) buses[to] = cost
  }

  val fr = i()
  val to = i()
  val q = PriorityQueue<Long>()
  q.add(fr.toLong())

  while (q.isNotEmpty()) {
    val e = q.poll()
    val acc = e / SEP
    val f = (e % SEP).toInt()
    if (cost[to] <= acc) continue
    val buses = g[f]
    for (e in buses) {
      val c = e.value
      val t = e.key
      val nAcc = acc + c
      if (cost[t] <= nAcc) continue
      cost[t] = nAcc.toInt()
      trace[t] = f
      q.add(nAcc * SEP + t)
    }
  }

  w(cost[to], true)

  var cnt = 0
  var prev = to
  while (prev != 0) {
    cost[cnt++] = prev
    val next = trace[prev]
    if (prev == fr || prev == next) break
    else prev = next
  }

  w(cnt, true)

  repeat(cnt) {
    w(cost[cnt - it - 1])
  }

  O.flush()
}
