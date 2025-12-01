package 백준.Silver.no11286

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1_200_000
private const val OBS = 1_000_000
private const val EOF = -1
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0

private fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

private val NUM = 48..57
private const val NEG = 45
private fun i(): Int {
  var v = 0
  var s = 1
  var c: Byte
  while (r().also { c = it } in NUM || c == NEG.toByte()) {
    when (c) {
      in NUM -> v = v * 10 + c - 48
      else -> s = -1
    }
  }
  return s * v
}


private const val WS = 10
private val WB = ByteArray(WS + 1).also { it[WS] = '\n'.code.toByte() }

private fun w(
  num: Int,
) {
  var v = num
  if (v < 0) {
    v = -v
    O.write(NEG)
  }
  var end = WS - 1
  do {
    WB[end--] = ((v % 10) + 48).toByte()
    v /= 10
  } while (v > 0)
  val stt = end + 1
  O.write(WB, stt, -stt + WS + 1)
}


fun main() {
  val n = i()
  val heap = IntArray(n + 1)
  var len = 0
  val root = 1

  fun abs(v: Int): Int = if (v < 0) -v else v

  fun isSmaller(
    v: Int,
    target: Int,
  ): Boolean = abs(v) > abs(target) || abs(v) == abs(target) && v > target

  fun insert(v: Int) {
    var ci = ++len
    heap[len] = v
    while (ci > root) {
      val pi = ci shr 1
      val p = heap[pi]
      val c = heap[ci]
      when {
        isSmaller(p, c) -> {
          heap[pi] = c
          heap[ci] = p
          ci = pi
        }

        else -> break
      }
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
      val end = li > len
      if (end) break
      val ri = li + 1
      var minIdx = li
      if (ri <= len && isSmaller(heap[li], heap[ri])) minIdx = ri
      val p = heap[pi]
      val min = heap[minIdx]
      if (isSmaller(p, min)) {
        heap[pi] = min
        heap[minIdx] = p
        pi = minIdx
      } else break
    }
    return v
  }

  repeat(n) {
    when (val v = i()) {
      0 -> w(pop())
      else -> insert(v)
    }
  }

  O.flush()
}
