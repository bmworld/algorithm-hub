import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.util.*

private const val IBS = 1 shl 8
private const val OBS = 1 shl 4
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

private const val SEP = 100_000
fun main() {
  val N = i()
  val D = i()

  val roads = Array<Road?>(N) { null }
  var len = 0
  repeat(N) {
    val fr = i()
    val to = i()
    val dist = i()
    if (to > D || to - fr <= dist) return@repeat
    val cur = Road(fr, to, dist)
    var i = len
    while (i > 0) {
      val prv = roads[i - 1]!!
      if (cur < prv) roads[i--] = prv
      else break
    }
    roads[i] = cur
    len++
  }

  val dists = IntArray(D + 1) { it }
  val q = PriorityQueue<Int>()
  val stt = 0
  q.add(stt)

  var loopFr = 0
  while (q.isNotEmpty()) {
    val e = q.poll()
    val pos = e / SEP
    val dist = e % SEP
    val lastDist = D - pos + dist
    if (dists[pos] < dist) continue
    if (dists[D] > lastDist) dists[D] = lastDist
    for (ri in loopFr until len) {
      val (fr, to, added) = roads[ri]!!
      val unusable = fr < pos
      if (pos > fr || unusable) continue
      val nd = dist + added + fr - pos
      if (dists[to] <= nd) continue
      dists[to] = nd
      q.add(encodePos(to, nd))
    }
    loopFr++
  }

  w(dists[D])
  O.flush()
}

private fun encodePos(
  pos: Int,
  dist: Int
) = pos * SEP + dist

data class Road(
  val fr: Int,
  val to: Int,
  val dist: Int
) : Comparable<Road> {

  override fun compareTo(o: Road): Int {
    val tfr = this.fr
    val ofr = o.fr
    return when {
      tfr == ofr -> this.dist.compareTo(o.dist)
      else -> tfr.compareTo(ofr)
    }
  }
}