package 백준.Silver.no2468

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 15
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
  val size = n * n

  var minH = MAX_H
  var maxH = MIN_H

  val sec = Array(MAX_H + 1) { mutableListOf<Int>() }
  val arr = IntArray(size)
  repeat(n) { r ->
    repeat(n) { c ->
      val h = i().also {
        if (it < minH) minH = it
        if (it > maxH) maxH = it
      }
      val e = pos(r, c, n)
      arr[e] = h
      sec[h].add(e)
    }
  }

  var ans = NO_RAIN_CNT
  val q = IntArray(size)

  var UID = MAX_H + 1
  var cnt = 0
  for (h in maxH downTo maxOf(minH, MIN_H + 1)) {
    val poses = sec[h]
    if (poses.isEmpty()) continue

    for (e in poses) {
      val r = e / n
      val c = e % n
      val p = pos(r, c, n)

      val (uid, mergedCnt) = getUidAndMergedCnt(arr, n, r, c, h, UID)
      cnt += 1 - mergedCnt
      if (uid == UID) UID++
      arr[p] = uid

      for (i in dr.indices) {
        val nr = r + dr[i]
        val nc = c + dc[i]
        if (nr !in 0 until n || nc !in 0 until n) continue
        val np = pos(nr, nc, n)
        val nh = arr[np]
        if (nh <= h || nh == uid) continue
        arr[np] = uid

        var qh = 0
        var qt = 0
        q[qt++] = np

        while (qh < qt) {
          val e2 = q[qh++]
          val r2 = e2 / n
          val c2 = e2 % n
          for (j in dr.indices) {
            val nr2 = r2 + dr[j]
            val nc2 = c2 + dc[j]
            if (nr2 !in 0 until n || nc2 !in 0 until n) continue
            val np2 = pos(nr2, nc2, n)
            val nh2 = arr[np2]
            if (nh2 <= h || nh2 == uid) continue
            arr[np2] = uid
            q[qt++] = np2
          }
        }
      }
    }

    if (cnt > ans) ans = cnt
  }

  w(ans)
  O.flush()
}

fun pos(r: Int, c: Int, CAP: Int): Int = r * CAP + c
fun getUidAndMergedCnt(arr: IntArray, n: Int, r: Int, c: Int, minH: Int,
  newUid: Int): Pair<Int, Int> {

  val oldUids = HashSet<Int>()
  var minUid = newUid

  for (i in dr.indices) {
    val nr = r + dr[i]
    val nc = c + dc[i]
    if (nr !in 0 until n || nc !in 0 until n) continue
    val p = pos(nr, nc, n)
    val h = arr[p]
    if (h <= minH) continue

    oldUids.add(h)
    if (h < minUid) minUid = h
  }

  return Pair(minUid, oldUids.size)
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
 */

/**
 * [RIVAL]
 * private val direction = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0))
 *
 * private var n = 0
 * private lateinit var board: Array<IntArray>
 * private lateinit var check: Array<BooleanArray>
 *
 * fun main() {
 *     n = i()
 *     board = Array(n) { IntArray(n) }
 *     var minHeight = 101
 *     var maxHeight = -1
 *     for (r in 0 until n) {
 *         for (c in 0 until n) {
 *             board[r][c] = i()
 *             if (board[r][c] > maxHeight) maxHeight = board[r][c]
 *             if (board[r][c] < minHeight) minHeight = board[r][c]
 *         }
 *     }
 *     var answer = 0
 *     var cnt: Int
 *     for (rainHeight in minHeight - 1 until maxHeight) {
 *         check = Array(n) { BooleanArray(n) }
 *         cnt = 0
 *         for (r in 0 until n) {
 *             for (c in 0 until n) {
 *                 if (board[r][c] > rainHeight && !check[r][c]) {
 *                     check[r][c] = true
 *                     cnt++
 *                     dfs(r, c, rainHeight)
 *                 }
 *             }
 *         }
 *         if (cnt > answer) answer = cnt
 *     }
 *     writeInt(answer)
 *     flushBuffer()
 * }
 *
 * private fun dfs(r: Int, c: Int, rainHeight: Int) {
 *     var nr: Int
 *     var nc: Int
 *     for (d in direction) {
 *         nr = r + d[0]
 *         nc = c + d[1]
 *
 *         if (nr in 0 until n && nc in 0 until n && board[nr][nc] > rainHeight && !check[nr][nc]) {
 *             check[nr][nc] = true
 *             dfs(nr, nc, rainHeight)
 *         }
 *     }
 * }
 *
 * private const val EOF = -1
 * private const val ASCII_minus: Byte = 45
 * private const val ASCII_0: Byte = 48
 * private val din = java.io.DataInputStream(System.`in`)
 * private val dout = java.io.DataOutputStream(System.out)
 * private val inbuffer = ByteArray(65536)
 * private var inbufferpointer = 0
 * private var bytesread = 0
 * private val outbuffer = ByteArray(65536)
 * private var outbufferpointer = 0
 * private val bytebuffer = ByteArray(20)
 *
 * private fun i(): Int {
 *     var v = 0
 *     var c = r()
 *     do v = v * 10 + c - 48 while (isDigit(r().also { c = it }))
 *     return v
 * }
 *
 * private fun r(): Byte {
 *     if (inbufferpointer == bytesread) fillBuffer()
 *     return if (bytesread == EOF) EOF.toByte() else inbuffer[inbufferpointer++]
 * }
 *
 * private fun fillBuffer() {
 *     bytesread = din.read(inbuffer, 0.also { inbufferpointer = it }, inbuffer.size)
 * }
 *
 * private fun writeInt(i: Int) {
 *     var i = i
 *     if (i == 0) {
 *         writeByte(ASCII_0)
 *     } else {
 *         if (i < 0) {
 *             writeByte(ASCII_minus)
 *             i = -i
 *         }
 *         var index = 0
 *         while (i > 0) {
 *             bytebuffer[index++] = (i % 10 + ASCII_0).toByte()
 *             i /= 10
 *         }
 *         while (index-- > 0) {
 *             writeByte(bytebuffer[index])
 *         }
 *     }
 * }
 *
 * private fun writeByte(b: Byte) {
 *     if (outbufferpointer == outbuffer.size) flushBuffer()
 *     outbuffer[outbufferpointer++] = b
 * }
 *
 * private fun flushBuffer() {
 *     if (outbufferpointer != 0) {
 *         dout.write(outbuffer, 0, outbufferpointer)
 *         outbufferpointer = 0
 *     }
 * }
 *
 * private fun isDigit(b: Byte) = b >= ASCII_0
 *
 */
