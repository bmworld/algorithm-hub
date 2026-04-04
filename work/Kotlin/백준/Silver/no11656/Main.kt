package 백준.Silver.no11656

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1_000
const val OBS = 1 shl 19
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
  var c: Byte
  while (r().also { c = it } in NUM || c == 45.toByte()) {
    when (c) {
      in NUM -> v = v * 10 + c - 48
      else -> s = -1
    }
  }
  return s * v
}

const val a: Byte = 97
const val z: Byte = 122
const val SEP = 10_000
fun main() {
  var S = ByteArray(1_000)
  var len = 0
  var b: Byte
  while (r().also { b = it } >= a) S[len++] = b

  val heap = MaxHeap(len)
  repeat(len) {
    val encodedChar = (S[it] - z) * -1
    heap.push(encodedChar * SEP + it)
  }

  while (heap.isNotEmpty()) {
    val idx = heap.pop() % SEP
    O.write(S, idx, len - idx)
    O.write(10)
  }

  O.flush()
}


class MaxHeap(size: Int) {

  var len = 0
  val root = 1
  val heap = IntArray(size + 1)

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
}
