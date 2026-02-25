package 백준.Gold.no14502

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 8
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

const val MAX_VIRUS_CNT = 10
const val MAX_USABLE_WALL_CNT = 3

val er = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
val ec = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)

fun main() {

  val ROW = i()
  val COL = i()
  val CAP = COL
  val SIZE = ROW * COL

  fun encodePos(r: Int, c: Int): Int = r * CAP + c
  val map = IntArray(SIZE)
  val virusPos = IntArray(MAX_VIRUS_CNT)
  var vCnt = 0
  var sCnt = 0

  repeat(ROW) { r ->
    repeat(COL) { c ->
      val v = i()
      val pos = encodePos(r, c)
      map[pos] = v
      when (v) {
        EMPTY -> sCnt++
        VIRUS -> virusPos[vCnt++] = pos
      }
    }
  }

  var max = 0
  val q = IntArray(SIZE)
  val quadDelta = intArrayOf(-1, 1, -CAP, CAP)

  fun dfs(dep: Int, stt: Int) {
    if (dep == MAX_USABLE_WALL_CNT) {
      var safeZone = sCnt - MAX_USABLE_WALL_CNT
      var qh = 0
      var qt = 0
      repeat(vCnt) {
        q[qt++] = virusPos[it]
      }

      val tmp = map.copyOf()
      bfs@ while (qh < qt) {
        val pos = q[qh++]
        for (i in 0..3) {
          val d = quadDelta[i]
          val nPos = pos + d
          if (pos % CAP == 0 && d == -1 || (pos + 1) % CAP == 0 && d == 1 || nPos !in 0 until SIZE || tmp[nPos] != EMPTY) continue
          tmp[nPos] = VIRUS
          if (--safeZone <= max) break@bfs
          else q[qt++] = nPos
        }
      }

      if (safeZone > max) max = safeZone
      return
    }

    for (pos in stt..SIZE - MAX_USABLE_WALL_CNT + dep) {
      if (map[pos] != EMPTY) continue
      val r = pos / CAP
      val c = pos % CAP
      var usable = false
      for (j in 0 until er.size) {
        val nr = r + er[j]
        val nc = c + ec[j]
        val outOfRange = !(nr in 0 until ROW && nc in 0 until COL)
        if (outOfRange || map[encodePos(nr, nc)] != EMPTY) {
          usable = true
          break
        }
      }
      if (usable) {
        map[pos] = WALL
        dfs(dep + 1, pos + 1)
        map[pos] = EMPTY
      }
    }
  }

  dfs(0, 0)
  w(max)
  O.flush()
}

/**
IN
3 3
0 0 0
0 0 0
2 2 0
OUT
4
 */
