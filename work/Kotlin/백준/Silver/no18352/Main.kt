package 백준.Silver.no18352

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

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

  var found = false

  val ch = BooleanArray(N + 1)
  val heap = HEAP(M)
  heap.push(stt)
  ch[stt] = true
  bfs@ while (heap.isNotEmpty()) {
    val e = heap.pop()
    val c = e / SEP
    val city = e % SEP
    val nc = c + 1
    if (c == K) {
      w(city)
      found = true
      continue
    }
    val list = g[city]
    repeat(list.size) {
      val next = list[it]
      if (ch[next]) return@repeat
      ch[next] = true
      if (nc <= K) heap.push(qPos(nc, next))
    }
  }

  if (!found) w(NOT_FOUND)
  O.flush()
}

fun qPos(
  cnt: Int,
  city: Int
): Int = cnt * SEP + city

private class HEAP(size: Int) {

  private var len = 0
  private val root = 1
  private val heap = IntArray(size + 1)

  fun isNotEmpty() = len > 0
  fun push(v: Int) {
    var ci = ++len
    heap[len] = v
    while (ci > root) {
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

  fun pop(): Int {
    if (len == 0) return 0

    val v = heap[root]
    heap[root] = heap[len]
    heap[len] = 0
    len--

    var pi = root
    while (true) {
      val li = pi shl 1
      if (li > len) break
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
