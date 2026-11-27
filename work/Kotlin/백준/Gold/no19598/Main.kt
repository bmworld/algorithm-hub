package 백준.Gold.no19598

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 18
const val OBS = 1 shl 4
val O = BufferedOutputStream(System.`out`, OBS)
val I = BufferedInputStream(System.`in`)
val IB = ByteArray(IBS)
var Ii = 0
var Il = 0
const val EOF = -1

fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

val NUM = 48..57
fun i(): Int {
  var v = 0
  var s = 1
  var b: Byte
  while (r().also { b = it } in NUM || b == 45.toByte()) {
    when (b) {
      in NUM -> v = v * 10 + b - 48
      else -> s = -1
    }
  }
  return s * v
}

const val WS = 10
val WB = ByteArray(WS)
fun w(
  num: Int
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

val SEP = 10_000_000_000UL

@OptIn(ExperimentalUnsignedTypes::class)
fun main() {

  val n = i()
  val meetings = ULongArray(n) { i().toULong() * SEP + i().toULong() }
  qs(meetings, 0, n - 1)
  val h = HEAP(n)

  for (i in 0 until n) {
    val etr = meetings[i]
    val s = (etr / SEP).toInt()
    val e = (etr % SEP).toInt()
    if (!h.isEmpty() && s >= h.top()) h.pop()
    h.push(e)
  }

  w(h.len)
  O.flush()
}

class HEAP(size: Int) {

  private val heap = IntArray(size + 1)
  private val root = 1
  var len = 0

  fun isEmpty() = len == 0

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
    val x = heap[len--]

    var pi = root
    var ci = root shl 1
    while (ci <= len) {
      val ri = ci + 1
      if (ri <= len && heap[ri] < heap[ci]) ci++
      if (heap[ci] >= x) break
      heap[pi] = heap[ci]
      pi = ci
      ci = pi shl 1
    }
    heap[pi] = x
    return v
  }

  fun top(): Int = heap[root]
}

@OptIn(ExperimentalUnsignedTypes::class)
fun swap(
  a: ULongArray,
  i: Int,
  j: Int,
) {
  val tmp = a[i]
  a[i] = a[j]
  a[j] = tmp
}

@OptIn(ExperimentalUnsignedTypes::class)
fun qs(
  a: ULongArray,
  l: Int,
  r: Int,
) {
  if (l >= r) return

  var pos = l
  var pl = l
  var pr = r
  val piv = a[(l + r) shr 1]

  while (pos <= pr) {
    val x = a[pos]
    when {
      x < piv -> swap(a, pos++, pl++)
      x > piv -> swap(a, pos, pr--)
      else -> pos++
    }
  }
  qs(a, l, pl - 1)
  qs(a, pr + 1, r)
}

/**
[IN]
8
14 16
16 18
8 16
20 30
3 10
0 5
14 24
4 6
[OUT]
3
 */
