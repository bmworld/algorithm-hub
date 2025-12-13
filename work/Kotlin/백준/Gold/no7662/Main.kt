package 백준.Gold.no7662

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 60_000
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

private fun op(): Byte {
  var c: Byte
  while (r().also { c = it } <= 32) {
  }
  r()
  return c
}

private const val WS = 10
private val WB = ByteArray(WS + 1).also { it[WS] = ' '.code.toByte() }
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
  O.write(WB, pos, WS - pos + 1)
}

private const val INSERT = 73.toByte()
private val EMPTY = byteArrayOf(69, 77, 80, 84, 89)
fun main() {

  val h = Heap(1_000_000)
  repeat(i()) {
    repeat(i()) {
      val op = op()
      val v = i()
      if (op == INSERT) h.insert(v)
      else if (!h.isEmpty()) {
        if (v == 1) h.removeLast() else h.removeFirst()
      }
    }

    if (h.isEmpty()) O.write(EMPTY)
    else {
      w(h.last())
      w(h.first())
    }
    O.write('\n'.code)
  }
  O.flush()
}

private class Heap(size: Int) {

  private val minHeap = IntArray(size + 1)
  private val maxHeap = IntArray(size + 1)
  private var len = 0
  private val root = 1
  fun first(): Int = minHeap[root]
  fun last(): Int = maxHeap[root]
  fun isEmpty(): Boolean = len == 0

  fun insert(v: Int) {
    len++
    insert(v, len, minHeap, true)
    insert(v, len, maxHeap, false)
  }

  private fun insert(
    v: Int,
    pos: Int,
    heap: IntArray,
    useMin: Boolean,
  ) {
    var ci = pos
    heap[pos] = v

    while (ci > root) {
      val pi = ci shr 1
      val p = heap[pi]
      val c = heap[ci]
      val comp = if (useMin) p > c else p < c
      if (comp) {
        heap[pi] = c
        heap[ci] = p
        ci = pi
      } else break
    }
  }

  fun removeFirst() {
    if (len == 0) return
    pop(minHeap, true)
    sync()
    len--
  }

  fun removeLast() {
    if (len == 0) return
    pop(maxHeap, false)
    sync()
    len--
  }

  private fun pop(
    heap: IntArray,
    useMin: Boolean,
  ): Int {
    if (len == 0) return 0

    val v = heap[root]
    heap[root] = heap[len]
    heap[len] = 0
    val nl = len - 1
    var pi = root

    while (true) {
      val li = pi shl 1
      val end = li > nl
      if (end) break
      val ri = li + 1
      var ci = li
      val compCs = if (useMin) heap[ri] < heap[li] else heap[ri] > heap[li]
      if (ri <= nl && compCs) ci = ri
      val p = heap[pi]
      val c = heap[ci]
      val compPC = if (useMin) p > c else p < c
      if (compPC) {
        heap[pi] = c
        heap[ci] = p
        pi = ci
      } else break
    }
    return v
  }


  private fun sync() {

    // minHeap - maxHeap 동기화.....
  }
}
