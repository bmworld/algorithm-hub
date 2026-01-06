import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 16
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
private val WB = ByteArray(WS + 1).also { it[WS] = 10 }
private fun w(
  num: Long,
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

private const val MAX = Long.MAX_VALUE
private val INF = byteArrayOf(73, 78, 70, 10)
private const val SEP = 100_000L
fun main() {
  val N = i()
  val E = i()
  val stt = i()

  val graph = Array(N + 1) { mutableListOf<Long>() }
  repeat(E) {
    val fr = i()
    val to = i()
    val w = i()
    graph[fr] += w * SEP + to
  }

  val dist = LongArray(N + 1) { MAX }
  dist[stt] = 0
  val heap = HEAP(300_001)
  heap.push(stt.toLong())


  while (heap.isNotEmpty()) {
    val e = heap.pop()
    val acc = e / SEP
    val fr = (e % SEP).toInt()

    val nodes = graph[fr]
    repeat(nodes.size) {
      val ne = nodes[it]
      val w = ne / SEP
      val to = (ne % SEP).toInt()
      val nw = acc + w
      if (dist[to] > nw) {
        dist[to] = nw
        heap.push(nw * SEP + to)
      }
    }
  }

  repeat(N) {
    val d = dist[it + 1]
    if (d == MAX) O.write(INF)
    else w(d)
  }

  O.flush()
}

private class HEAP(size: Int) {

  private var len = 0
  private val ROOT = 1
  private val heap = LongArray(size)

  fun isNotEmpty() = len > 0
  fun push(v: Long) {
    var ci = ++len
    heap[len] = v
    while (ci > ROOT) {
      val pi = ci shr 1
      val p = heap[pi]
      val c = heap[ci]
      if (p > c) {
        heap[pi] = c
        heap[ci] = p
        ci = pi
      } else break
    }

  }

  fun pop(): Long {
    if (len == 0) return 0

    val v = heap[ROOT]
    heap[ROOT] = heap[len]
    heap[len] = 0
    len--

    var pi = ROOT
    while (true) {
      val li = pi shl 1
      val end = li > len
      if (end) break
      val ri = li + 1
      var minIdx = li
      if (ri <= len && heap[ri] < heap[li]) minIdx = ri
      val p = heap[pi]
      val min = heap[minIdx]
      if (p > min) {
        heap[pi] = min
        heap[minIdx] = p
        pi = minIdx
      } else break
    }
    return v
  }
}