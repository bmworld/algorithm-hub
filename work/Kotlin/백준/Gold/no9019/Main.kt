package 백준.Gold.no9019

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 20_000
private const val OBS = 6_000
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

private const val EMPTY = 0.toByte()
private const val D = 68.toByte()
private const val S = 83.toByte()
private const val L = 76.toByte()
private const val R = 82.toByte()
private val ops = byteArrayOf(D, S, L, R)
private const val CAP = 10_000
private const val SEP = CAP
fun main() {
  val q = IntArray(CAP)
  repeat(i()) {
    val usedOps = ByteArray(CAP) { EMPTY }
    val froms = IntArray(CAP)
    val from = i()
    val to = i()
    var qh = 0
    var qt = 0
    usedOps[from] = 0
    q[qt++] = from
    bfs@ while (qh < qt) {
      val v = q[qh++]
      for (i in 0..3) {
        val op = ops[i]
        val nv = fwd(v, op)
        if (usedOps[nv] == EMPTY) {
          usedOps[nv] = op
          froms[nv] = v
          if (nv == to) break@bfs
          q[qt++] = nv
        }
      }
    }

    var v = to
    var cnt = 0
    while (v != from) {
      cnt++
      v = froms[v]
    }
    val tracedOps = ByteArray(cnt)

    v = to
    while (cnt > 0) {
      tracedOps[--cnt] = usedOps[v]
      v = froms[v]
    }

    O.write(tracedOps)
    O.write('\n'.code)
  }
  O.flush()
}

private fun fwd(
  v: Int,
  op: Byte,
): Int {
  return when (op) {
    D -> v shl 1

    S -> v - 1 + CAP

    L -> v * 10 + v / 1000

    R -> (v % 10) * 1000 + v / 10

    else -> throw Exception()
  } % CAP
}
