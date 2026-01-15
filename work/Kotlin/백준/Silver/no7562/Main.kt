package 백준.Silver.no7562

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 12
private const val OBS = 1 shl 8
private val O = BufferedOutputStream(System.out, OBS)
private val I = BufferedInputStream(System.`in`)
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

private const val WS = 10
private val WB = ByteArray(WS + 1).also { it[WS] = 10 }
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
  O.write(WB, ++pos, WS - pos + 1)
}

private const val MAX_W = 300
private const val RC_SEP = 1_000
private const val CNT_SEP = RC_SEP * RC_SEP
private const val SAFE_DIST = 4

private val dr = intArrayOf(1, 2, 2, 1, -1, -2, -2, -1)
private val dc = intArrayOf(2, 1, -1, -2, -2, -1, 1, 2)

fun main() {

  val q = IntArray(MAX_W * MAX_W)
  repeat(i()) {
    val size = i()
    var fr = i()
    var fc = i()
    var tr = i()
    var tc = i()
    if (fr == tr && fc == tc) {
      w(0)
      return@repeat
    }

    var distR = getDist(fr, tr)
    var distC = getDist(fc, tc)

    when {
      fr >= tr && fc >= tc -> { // flip R, C
        val tmpR = fr
        fr = tr
        tr = tmpR
        val tmpC = fc
        fc = tc
        tc = tmpC
      }
      fr > tr && fc <= tc && (fr + 1 < size && tr > 0) -> { // flip R
        fr -= distR
        tr += distR
      }
      fr <= tr && fc > tc && (fc + 1 < size && tc > 0) -> { // flip C
        fc -= distC
        tc += distC
      }
    }

    var moved = 0
    while (distR > SAFE_DIST || distC > SAFE_DIST) {

      if (distR >= distC) {
        fr += if (tr > fr) 2 else -2
        distR -= 2
        val dist = if (inRange(fr, fc + 1, size) && getDist(fc + 1, tc) <= getDist(fc - 1, tc)) 1 else -1
        fc += dist
        distC -= if (tc > fc) dist else -dist
      } else {
        fc += if (tc > fc) 2 else -2
        distC -= 2
        val dist = if (inRange(fr + 1, fc, size) && getDist(fr + 1, tr) <= getDist(fr - 1, tr)) 1 else -1
        fr += dist
        distR -= if (tr > fr) dist else -dist
      }
      moved++
    }

    val ch = BooleanArray(size * size)
    var qh = 0
    var qt = 0
    ch[encodePos(fr, fc, size)] = true
    if (fr != tr || fc != tc) q[qt++] = qPos(moved, fr, fc)

    bfs@ while (qh < qt) {
      val e = q[qh++]
      val cnt = e / CNT_SEP
      val rc = e % CNT_SEP
      val r = rc / RC_SEP
      val c = rc % RC_SEP
      val nCnt = cnt + 1
      for (i in 0..7) {
        val nr = r + dr[i]
        val nc = c + dc[i]
        val nPos = encodePos(nr, nc, size)
        if (!inRange(nr, nc, size) || ch[nPos]) continue

        if (nr == tr && nc == tc) {
          moved = nCnt
          break@bfs
        }

        ch[nPos] = true
        q[qt++] = qPos(nCnt, nr, nc)
      }
    }

    w(moved)
  }
  O.flush()
}

private fun inRange(
  r: Int,
  c: Int,
  size: Int
): Boolean = r in 0 until size && c in 0 until size

private fun encodePos(
  r: Int,
  c: Int,
  CAP: Int
): Int = r * CAP + c

private fun qPos(
  cnt: Int,
  r: Int,
  c: Int
) = cnt * CNT_SEP + r * RC_SEP + c

private fun getDist(
  a: Int,
  b: Int
): Int {
  val v = a - b
  return if (v > 0) v else -v
}

// println("-- ORGN: $fr, $fc -> $tr, $tc (dist = $distR, &$distC)")
// println("-- NEXT: $fr, $fc -> $tr, $tc (dist = $distR, &$distC)")
//println("-- MOVE: $fr, $fc -> $tr, $tc (dist = $distR, $distC) ---- $fastMoved")

// println("-- BFS: $fr, $fc -> $tr, $tc")
//println("---- [case ${it + 1}] $r, $c ($cnt)-> $nr, $nc ($nCnt)")
