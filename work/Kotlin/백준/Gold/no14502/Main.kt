package 백준.Gold.no14502

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 10
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

const val EMPTY = 0
const val WALL = 1
const val VIRUS = 2
const val SEP = 10

val dr = intArrayOf(0, 1, 0, -1)
val dc = intArrayOf(1, 0, -1, 0)
const val MAX_USABLE_WALL_CNT = 3
const val MAX_VIRUS_CNT = 10

fun main() {

  val ROW = i()
  val COL = i()
  val CAP = COL
  fun pos(r: Int, c: Int): Int = r * CAP + c

  val SIZE = ROW * COL
  val map = IntArray(SIZE)

  var eCnt = 0
  val virusPos = IntArray(MAX_VIRUS_CNT)
  var vCnt = 0
  val safePos = IntArray(SIZE)
  var sCnt = 0
  repeat(ROW) { r ->
    repeat(COL) { c ->
      val v = i()
      when (v) {
        EMPTY -> eCnt++
        VIRUS -> virusPos[vCnt++] = r * SEP + c
      }
      map[pos(r, c)] = v
    }
  }

  var max = 0
  val q = IntArray(SIZE)
  val infected = IntArray(SIZE)
  fun searchSafeZone(init: Boolean): Int {
    var cnt = eCnt - MAX_USABLE_WALL_CNT
    var qh = 0
    var qt = 0
    repeat(vCnt) {
      q[qt++] = virusPos[it]
    }
    var iCnt = 0
    bfs@ while (qh < qt) {
      val vPos = q[qh++]
      val r = vPos / SEP
      val c = vPos % SEP
      for (i in 0..3) {
        val nr = r + dr[i]
        val nc = c + dc[i]
        val nPos = pos(nr, nc)

        if (nr in 0 until ROW && nc in 0 until COL && map[nPos] == EMPTY) {
          map[nPos] = VIRUS
          infected[iCnt++] = nPos
          if (init) safePos[sCnt++] = nr * SEP + nc
          else if (--cnt <= max) break@bfs

          q[qt++] = nr * SEP + nc
        }
      }
    }

    repeat(iCnt) {
      map[infected[it]] = EMPTY
    }
    return cnt
  }

  searchSafeZone(true)

  fun dfs(dep: Int, stt: Int) {
    if (dep == MAX_USABLE_WALL_CNT) {
      searchSafeZone(false).also { if (it > max) max = it }
      return
    }
    for (i in stt until sCnt) {
      val rc = safePos[i]
      val pos = pos(rc / SEP, rc % SEP)
      map[pos] = WALL
      dfs(dep + 1, i + 1)
      map[pos] = EMPTY
    }
  }

  dfs(0, 0)
  w(max)
  O.flush()
}

//println("[$pos] = $stt")
//println("--- $r, $c -> $nr, $nc")
