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
  val r = Array<Road?>(N) { null }
  val dists = IntArray(D + 1) { it }

  var len = 0
  repeat(N) {
    val fr = i()
    val to = i()
    val dist = i()
    if (to > D || to - fr <= dist) return@repeat
    var i = len
    var override = false
    while (i > 0) {
      val prv = getRoad(r, i - 1, len)!!
      val prvFr = prv.fr
      if (fr < prvFr) r[i--] = prv
      else if (fr == prvFr && to == prv.to) {
        if (dist >= prv.d) return@repeat
        else {
          i--
          override = true
          break
        }
      } else if (fr < prvFr) r[i--] = prv
      else break
    }
    r[i] = Road(fr, to, dist)
    if (!override) len++
  }

  var i = 0
  var road = getRoad(r, i, len)
  repeat(D) { cur ->
    val nxt = cur + 1
    dists[nxt] = minOf(dists[nxt], dists[cur] + 1)
    if (road == null || road!!.fr != cur) return@repeat

    while (i < len) {
      val (fr, to, d) = road!!
      if (cur != fr) break
      dists[to] = minOf(dists[to], dists[to - 1] + 1, dists[fr] + d)
      if (i + 1 < len) road = getRoad(r, ++i, len)
      else break
    }
  }

  w(dists[D])
  O.flush()
}

data class Road(
  val fr: Int,
  val to: Int,
  val d: Int
)

private fun getRoad(
  roads: Array<Road?>,
  ri: Int,
  len: Int
): Road? = if (len > 0) roads[ri] else null