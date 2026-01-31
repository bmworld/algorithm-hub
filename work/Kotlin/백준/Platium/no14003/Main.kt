package 백준.Platium.no14003

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.util.*

const val IBS = 1 shl 18
const val OBS = 1 shl 18
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
val WB = ByteArray(WS + 1)
fun w(
  num: Int,
  end: Boolean = false
) {
  WB[WS] = if (end) 10 else 32
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

const val EMPTY = -1
fun main() {
  val N = i()
  val lis = IntArray(N)
  val orgn = IntArray(N)
  var li = EMPTY
  var lastPos = 0
  repeat(N) { i ->
    val v = i()
    orgn[i] = v

    if (li == EMPTY || v > lis[li]) {
      lis[++li] = v
      lastPos = i
    } else updateLis(lis, v, 0, li)
  }

  var cnt = li + 1
  w(cnt, true)

  val sorted = Arrays.copyOfRange(orgn, 0, lastPos + 1)

  fun swap(
    a: IntArray,
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun `3way_qs`(
    a: IntArray,
    l: Int,
    r: Int,
  ): Pair<Int, Int> {
    var pos = l
    var pl = l
    var pr = r
    val piv = a[(l + r) shr 1]

    while (pos <= pr) {
      val v = a[pos]
      when {
        v < piv -> {
          swap(a, pos, pl)
          pl++
          pos++
        }
        v > piv -> {
          swap(a, pos, pr)
          pr--
        }
        else -> pos++
      }
    }
    return Pair(pl, pr)
  }

  fun qs(
    a: IntArray,
    l: Int,
    r: Int,
  ) {
    if (l >= r) return
    val (pl, pr) = `3way_qs`(a, l, r)
    qs(a, l, pl - 1)
    qs(a, pr + 1, r)
  }

  qs(sorted, 0, lastPos)

  var limit = lis[li--]
  val foundPos = binarySearch(sorted, limit)
  var i = if (foundPos == EMPTY) lastPos - 1 else foundPos
  while (i >= 0 && li >= 0) {
    val v = sorted[i--]
    if (limit > v) lis[li--] = v.also { limit = it }
  }

  repeat(cnt) {
    w(lis[it])
  }

  O.flush()
}

fun updateLis(
  lis: IntArray,
  v: Int,
  stt: Int,
  end: Int
) {
  var l = stt
  var r = end
  while (l < r) {
    val m = (l + r) shr 1
    var mv = lis[m]
    when {
      mv < v -> l = m + 1
      else -> r = m
    }
  }
  lis[l] = v
}

fun binarySearch(
  a: IntArray,
  v: Int
): Int {
  var l = 0
  var r = a.size - 1
  while (l <= r) {
    val m = (l + r) shr 1
    val mv = a[m]
    when {
      mv == v -> return m
      mv > m -> r = m - 1
      else -> l = m + 1
    }
  }
  return EMPTY
}
//    println("[${i + 1}] $v / limit=$limit")
