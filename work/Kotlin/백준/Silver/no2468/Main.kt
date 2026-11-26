package 백준.Silver.no2468

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 17
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

const val NO_RAIN_CNT = 1
const val MIN_H = 1
const val MAX_H = 100
val dr = intArrayOf(0, 1, 0, -1)
val dc = intArrayOf(1, 0, -1, 0)
fun main() {
  val n = i()

  var minH = MAX_H
  var maxH = MIN_H

  val sec = Array(MAX_H + 1) { mutableListOf<Int>() }

  val size = n * n
  val arr = IntArray(size)
  val tree = IntArray(size) { it }
  val treeSize = IntArray(size) { 1 }
  repeat(n) { r ->
    repeat(n) { c ->
      val h = i().also {
        if (it < minH) minH = it
        if (it > maxH) maxH = it
      }
      val root = pos(r, c, n)
      arr[root] = h
      sec[h].add(root)
    }
  }

  var ans = NO_RAIN_CNT
  var cnt = 0
  for (h in maxH downTo maxOf(minH, MIN_H + 1)) {
    for (root in sec[h])
      cnt += 1 - unionWithSib(arr, n, h, tree, root, treeSize)

    if (cnt > ans) ans = cnt
  }

  w(ans)
  O.flush()
}

fun pos(r: Int, c: Int, CAP: Int): Int = r * CAP + c
fun unionWithSib(arr: IntArray, n: Int, h: Int, tree: IntArray, root: Int,
  treeSize: IntArray): Int {

  var merged = 0
  val r = root / n
  val c = root % n
  repeat(4) { i ->
    val r2 = r + dr[i]
    val c2 = c + dc[i]
    if (r2 !in 0 until n || c2 !in 0 until n) return@repeat
    val p2 = pos(r2, c2, n)
    val h2 = arr[p2]
    if (h2 <= h) return@repeat
    val root2 = find(p2, tree)
    if (union(root, root2, tree, treeSize)) merged++
  }

  arr[root] = h + 1 // 인접한 같은높이 처리용 Trick

  return merged
}

fun find(x: Int, tree: IntArray): Int {
  val r = tree[x]
  return if (x == r) r
  else find(r, tree).also { tree[x] = it }
}

fun union(a: Int, b: Int, tree: IntArray, treeSize: IntArray): Boolean {
  var ra = find(a, tree)
  var rb = find(b, tree)
  if (ra == rb) return false

  val szA = treeSize[ra]
  val szB = treeSize[rb]
  if (szA < szB) {
    val tmp = ra
    ra = rb
    rb = tmp
  }

  tree[rb] = ra
  treeSize[ra] += szB

  return true
}

/**
[IN]
8
3 1 4 8 5 1 4 4
2 2 8 1 5 4 7 3
2 7 5 1 3 4 1 1
4 2 5 1 8 3 1 7
1 4 1 3 4 8 2 8
2 6 1 6 1 7 4 3
1 1 6 5 8 6 7 1
6 5 2 8 2 8 6 5

[OUT]
12

[IN]
11
11 13 7 5 13 13 15 4 14 4 4
16 1 10 9 13 21 10 6 5 5 9
11 14 13 9 6 21 20 9 9 9 18
16 12 3 20 12 4 8 21 5 1 17
7 11 11 4 15 2 14 10 18 18 21
7 5 5 3 15 2 15 6 11 11 9
17 1 1 8 6 19 19 2 19 17 19
15 12 10 11 11 14 10 7 17 10 11
14 18 4 6 14 3 10 20 1 13 11
17 20 14 10 3 2 2 4 12 15 4
21 2 18 9 17 14 2 21 2 9 13

[OUT]
19

[IN]
14
7 15 28 25 27 13 4 24 3 6 25 29 15 22
15 27 21 24 4 24 15 20 25 25 12 13 13 10
17 10 23 16 24 27 28 2 17 1 27 13 3 17
11 15 27 24 21 10 8 17 9 2 25 8 11 11
27 4 9 15 8 5 23 19 10 3 25 9 7 6
2 5 6 25 24 22 15 27 7 12 13 10 18 13
2 15 28 12 14 14 4 7 22 4 26 21 7 21
10 24 18 1 24 19 20 28 6 2 5 10 20 7
5 4 10 2 28 24 15 11 1 25 29 5 2 28
17 17 28 8 1 26 21 6 14 27 28 26 6 6
4 18 17 6 20 14 3 14 3 1 15 23 22 10
24 20 13 17 19 28 14 9 15 12 9 12 6 9
9 27 18 2 11 24 18 29 6 7 3 27 20 22
29 26 15 17 26 25 26 3 18 7 7 23 9 18

[OUT]
28

[IN]
8
39 44 49 54 59 64 69 74
44 49 54 59 64 69 74 79
49 54 59 64 69 74 79 84
54 59 64 69 74 79 84 89
59 64 69 74 79 84 89 94
64 69 74 79 84 89 94 99
69 74 79 84 89 94 99 100
74 79 84 89 94 99 100 100

[OUT]
1
 */
