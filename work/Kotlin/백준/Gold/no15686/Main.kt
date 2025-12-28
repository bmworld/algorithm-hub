package 백준.Gold.no15686

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 10_000
private const val OBS = 1_000
private val O = BufferedOutputStream(System.`out`, OBS)
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
private val WB = ByteArray(WS)
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
  pos++
  O.write(WB, pos, WS - pos)
}

private const val X: Byte = 48
private const val H: Byte = 49
private const val C: Byte = 50
private const val SEP = 100

private fun s(): Byte {
  var c: Byte
  while (r().also { c = it } >= 10) if (c in X..C) break
  return c
}

fun main() {
  val N = i()
  val M = i()
  val hPos = IntArray(2 * N)
  val cPos = IntArray(13)
  var hLen = 0
  var cLen = 0
  repeat(N) { r ->
    repeat(N) { c ->
      when (s()) {
        H -> hPos[hLen++] = r * SEP + c
        C -> cPos[cLen++] = r * SEP + c
      }
    }
  }

  val a = Array(cLen) { IntArray(hLen) }
  repeat(cLen) { ci ->
    val ce = cPos[ci]
    val cr = ce / SEP
    val cc = ce % SEP
    repeat(hLen) { hi ->
      val he = hPos[hi]
      val hr = he / SEP
      val hc = he % SEP
      val dist = getDist(cr, cc, hr, hc)
      a[ci][hi] = dist
    }
  }

  var minAcc = Int.MAX_VALUE
  val used = IntArray(M)
  fun dfs(
    stt: Int,
    dep: Int,
  ) {
    if (dep == M) {
      var acc = 0
      repeat(hLen) { hi ->
        var minDist = Int.MAX_VALUE
        repeat(M) { ci ->
          val v = a[used[ci]][hi]
          if (v < minDist) minDist = v
        }
        acc += minDist
      }

      if (minAcc > acc) minAcc = acc
      return
    }

    val end = cLen - M + dep
    for (i in stt..end) {
      used[dep] = i
      dfs(i + 1, dep + 1)
    }
  }

  dfs(0, 0)

  w(minAcc)
  O.flush()
}

fun getDist(
  r1: Int,
  c1: Int,
  r2: Int,
  c2: Int,
): Int {
  var w = r1 - r2
  if (w < 0) w = -w
  var h = c1 - c2
  if (h < 0) h = -h
  return w + h
}
