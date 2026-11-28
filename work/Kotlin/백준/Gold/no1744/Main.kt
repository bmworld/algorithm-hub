package 백준.Gold.no1744

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 9
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

fun main() {

  val n = i()
  val minH = MinHeap(n)
  val maxH = MaxHeap(n)
  repeat(n) {
    val x = i()
    minH.push(x)
    maxH.push(x)
  }

  var ans = 0
  while (minH.len >= 2) {
    val a = minH.pop()
    val b = minH.pop()
    when {
      a >= 0 -> break
      b > 0 -> {
        ans += a
        break
      }
      else -> ans += a * b
    }
  }
  if (minH.isNotEmpty() && minH.top() < 0) ans += minH.top()


  while (maxH.len >= 2) {
    val a = maxH.pop()
    val b = maxH.pop()
    when {
      a <= 0 -> break
      b <= 0 -> {
        ans += a
        break
      }
      else -> ans += maxOf(a * b, a + b)
    }
  }
  if (maxH.isNotEmpty() && maxH.top() > 0) ans += maxH.top()

  w(ans)
  O.flush()
}


class MinHeap(size: Int) {

  val heap = IntArray(size + 1)
  val root = 1
  var len = 0

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

class MaxHeap(size: Int) {

  val heap = IntArray(size + 1)
  val root = 1
  var len = 0

  fun isNotEmpty() = len > 0

  fun push(v: Int) {
    var ci = ++len
    heap[len] = v
    while (ci > root) {
      val pi = ci shr 1
      val p = heap[pi]
      val c = heap[ci]
      if (p < c) {
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
      if (ri <= len && heap[ri] > heap[ci]) ci++
      if (heap[ci] <= x) break
      heap[pi] = heap[ci]
      pi = ci
      ci = pi shl 1
    }
    heap[pi] = x
    return v
  }

  fun top(): Int = heap[root]
}

/**
[IN]
2
1
9
[OUT]
10

[IN]
3
-1
0
1
[OUT]
1

[IN]
2
-1
0
[OUT]
0

[IN]
3
-1
0
1
[OUT]
1
 */
