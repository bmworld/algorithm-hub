package 백준.Gold.no27440

import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.util.*

private const val IBS = 1 shl 5
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
private fun i(): Long {
  var v = 0L
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
  O.write(WB, ++pos, WS - pos + 1)
}

private const val INF = 999

fun main() {
  val N = i()
  var opCnt = INF

  val ch = HashMap<Long, Int>()
  val q = PriorityQueue(compareBy<Num> { it.cnt }.thenBy { it.v })
  q.add(Num(0, N))

  while (q.isNotEmpty()) {
    val e = q.poll()
    val cnt = e.cnt
    val fr = e.v
    if (fr == 1L) {
      opCnt = cnt
      break
    }

    for (num in 3 downTo 2) {
      val to = fr / num
      val nc = (cnt + 1 + fr % num).toInt()
      if ((ch[to] ?: INF) <= nc) continue
      ch[to] = nc
      q.add(Num(nc, to))
    }
  }

  w(opCnt)
  O.flush()
}

private data class Num(
  var cnt: Int,
  val v: Long,
)
