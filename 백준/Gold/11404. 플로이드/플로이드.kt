import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 18
private const val OBS = 1 shl 16
private val O = BufferedOutputStream(System.`out`, OBS)
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
private val WB = ByteArray(WS + 1)
private fun w(
  num: Int,
  end: Boolean = false,
) {
  WB[WS] = if (end) 10 else 32
  var v = num
  var pos = WS - 1
  do {
    WB[pos--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  O.write(WB, ++pos, WS - pos + 1)
}

private const val INF = 1_000_000_000
fun main() {
  val N = i()
  val M = i()
  val CAP = N + 1

  fun encodePos(
    r: Int,
    c: Int,
  ) = r * CAP + c

  val SIZE = (N + 1) * CAP
  val costs = IntArray(SIZE) { i ->
    val r = i / CAP
    val c = i % CAP
    if (r == c) 0 else INF
  }

  repeat(M) {
    val fr = i()
    val to = i()
    val next = i()

    val pos = encodePos(fr, to)
    val prev = costs[pos]
    if (next < prev) costs[pos] = next
  }

  for (mid in 1..N) {
    for (fr in 1..N) {
      if (mid == fr) continue
      for (to in 1..N) {
        if (fr == to) continue
        val path = encodePos(fr, to)
        val prev = costs[path]
        val next = costs[encodePos(fr, mid)] + costs[encodePos(mid, to)]
        if (prev > next) costs[path] = next
      }
    }
  }

  repeat(N) { i ->
    val r = i + 1
    repeat(N) { j ->
      val c = j + 1
      val cost = costs[encodePos(r, c)]
      w(if (cost == INF) 0 else cost, c == N)
    }
  }

  O.flush()
}