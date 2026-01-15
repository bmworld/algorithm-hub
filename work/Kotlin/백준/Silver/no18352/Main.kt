package 백준.Silver.no18352

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.util.*

private const val IBS = 1 shl 16
private const val OBS = 1 shl 12
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
  O.write(WB, ++pos, WS - pos + 1)
}

private const val SEP = 1_000_000
private const val NOT_FOUND = -1

fun main() {
  val N = i()
  val M = i()
  val K = i()
  val stt = i()

  val g = Array(N + 1) { mutableListOf<Int>() }
  repeat(M) {
    val fr = i()
    val to = i()
    g[fr] += to
  }

  val ch = BooleanArray(N + 1)

  val q = PriorityQueue<Int>()
  q.add(stt)
  ch[stt] = true
  val targets = PriorityQueue<Int>()

  bfs@ while (q.isNotEmpty()) {

    val e = q.poll()
    val c = e / SEP
    val nc = c + 1
    val list = g[e % SEP]
    repeat(list.size) {
      val city = list[it]
      if (ch[city]) return@repeat
      ch[city] = true
      if (nc == K) targets.add(city) else q.add(qPos(nc, city))
    }
  }

  if (targets.isEmpty()) w(NOT_FOUND)
  else while (targets.isNotEmpty()) w(targets.poll())
  O.flush()
}

fun qPos(
  cnt: Int,
  city: Int
): Int = cnt * SEP + city
