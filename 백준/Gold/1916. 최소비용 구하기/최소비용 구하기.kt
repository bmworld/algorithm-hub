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
  pos++
  O.write(WB, pos, WS - pos)
}

private const val INF = Int.MAX_VALUE
private const val SEP = 10_000

fun main() {
  val n = i()
  val m = i()

  val g = Array(n + 1) { mutableMapOf<Int, Int>() }
  val cost = Array(n + 1) { INF }
  repeat(m) {
    val fr = i()
    val to = i()
    val c = i()
    if ((g[fr][to] ?: INF) > c) g[fr][to] = c
  }

  val fr = i().toLong()
  val to = i()
  val q = PriorityQueue<Long>()
  q.add(fr)
  while (q.isNotEmpty()) {
    val e = q.poll()
    val acc = e / SEP
    val f = e % SEP
    if (cost[to] <= acc) continue
    for (etr in g[f.toInt()]) {
      val t = etr.key
      val c = etr.value.toLong()
      val nAcc = acc + c
      if (fr != f && cost[t] <= nAcc) continue
      cost[t] = nAcc.toInt()
      q.add(nAcc * SEP + t)
    }
  }
  w(cost[to])
  O.flush()
}