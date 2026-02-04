package 백준.Gold.no1504

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 16
const val OBS = 1 shl 3
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

const val WS = 10
val WB = ByteArray(WS)
fun w(
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
  O.write(WB, ++pos, WS - pos)
}

const val SEP = 1000L
const val NOT_FOUND = -1L
const val INF = Long.MAX_VALUE
const val MAX_EDGES = 200_000

fun main() {
  val stt = 1
  val end = i()

  val heap = HEAP(MAX_EDGES + 1)
  val graph = Array(end + 1) { mutableListOf<Long>() }

  repeat(i()) {
    val fr = i()
    val to = i()
    val w = i()
    graph[fr] += w * SEP + to
    graph[to] += w * SEP + fr
  }

  val v1 = i()
  val v2 = i()

  val d1 = getDist(stt, v1, heap, graph, end)
  var d2 = getDist(v1, v2, heap, graph, end)
  if (stt == v1 || v2 == end) d2 = minOf(d2, getDist(stt, end, heap, graph, end))
  val d3 = getDist(v2, end, heap, graph, end)

  w(getSumOfDists(d1, d2, d3))
  O.flush()
}

fun getSumOfDists(d1: Long, d2: Long = 0, d3: Long = 0): Long =
  if (d1 == INF || d2 == INF || d3 == INF) NOT_FOUND else d1 + d2 + d3

fun getDist(
  stt: Int,
  end: Int,
  heap: HEAP,
  graph: Array<MutableList<Long>>,
  size: Int,
): Long {
  if (stt == end) return 0
  heap.clear()

  val dist = LongArray(size + 1) { INF }

  dist[stt] = 0
  heap.push(stt.toLong())
  bfs@ while (heap.isNotEmpty()) {
    val e = heap.pop()
    val acc = e / SEP
    val fr = (e % SEP).toInt()
    if (fr == end) break@bfs
    if (dist[fr] < acc) continue
    for (ne in graph[fr]) {
      val w = ne / SEP
      val to = (ne % SEP).toInt()
      val nw = acc + w
      if (dist[to] > nw) {
        dist[to] = nw
        heap.push(nw * SEP + to)
      }
    }
  }

  println("---- $stt->$end")
  for (i in stt..end) {
    println("--- dist[$i] = ${dist[i]}")
  }
  return dist[end]
}

class HEAP(size: Int) {

  val heap = LongArray(size + 1)
  val root = 1
  var len = 0

  fun clear() {
    len = 0
  }

  fun isNotEmpty() = len > 0

  fun push(v: Long) {
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

  fun pop(): Long {
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
}

/**
// ---------------------------------------------------------------------
IN
2 0
1 2
OUT
-1
// ---------------------------------------------------------------------
IN
2 1
1 2 7
1 2
OUT
7
// ---------------------------------------------------------------------
IN
5 1
1 3 8
2 3
OUT
-1
// ---------------------------------------------------------------------
IN
3 2
1 2 6
1 3 4
2 3
OUT
16 ------???? (10 아님???)
// ---------------------------------------------------------------------
IN
3 2
1 3 2
2 3 3
2 3
OUT
8
// ---------------------------------------------------------------------
IN (***** 의문인 케이스..)
3 3
1 2 8
1 3 3
2 3 4
2 3
OUT
11
// ---------------------------------------------------------------------
IN
5 6
1 2 7
4 5 8
2 5 7
2 3 3
3 4 3
3 5 1
2 5
OUT
11
// ---------------------------------------------------------------------
IN
4 6
2 3 10
1 3 6
2 4 4
1 4 2
3 4 10
1 2 4
3 2
OUT
20
// ---------------------------------------------------------------------
IN
6 14
4 6 4
4 5 6
2 3 2
3 4 1
1 6 4
2 5 8
1 2 7
2 6 2
2 4 2
1 3 9
3 6 8
5 6 8
1 4 5
1 5 9
2 5
OUT
19
// ---------------------------------------------------------------------
IN
7 17
1 6 2
2 7 4
1 5 3
1 4 4
1 7 7
4 5 7
1 2 10
1 3 9
3 6 3
5 7 3
3 7 7
4 6 9
4 7 9
3 5 9
6 7 5
2 5 5
2 6 9
6 4
OUT
15
// ---------------------------------------------------------------------
IN
9 17
2 6 1
2 8 1
1 2 1
5 7 10
4 7 2
1 7 6
7 9 2
3 9 10
4 8 4
3 8 3
1 4 9
1 3 3
6 7 4
2 7 9
2 4 7
1 8 8
4 5 1
7 5
OUT
12
 */

//      println("d= $d1, $d2, $d3")
//println("IN: $fr -> $to ($w)")
//for (fr in 1 .. end) for (to in graph[fr]) println("[NODE] $fr -> $to ")
//println("- dist[$fr]= ${dist[fr]}  ->dist[$to]  ${dist[to]}  vs $nw")
