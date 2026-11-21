package 백준.Silver.no19621

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
  val N = i()
  val arr = Array(N) { i -> Meeting(i(), i(), i()) }
  qs(arr, 0, N - 1)

  var best = 0
  fun dfs(i: Int, sum: Int, dep: Int) {
    if (i == N) {
      if (sum > best) best = sum
      return
    }

    val cur = arr[i]
    var ni = N
    for (j in i + 1 until N) if (arr[j].s >= cur.e) {
      ni = j
      break
    }

    dfs(ni, sum + cur.c, dep + 1)

    dfs(i + 1, sum, dep + 1)
  }

  for (i in arr.indices) dfs(i, 0, 0)

  w(best)
  O.flush()
}

data class Meeting(val s: Int, val e: Int, val c: Int)

fun swap(a: Array<Meeting>, i: Int, j: Int) {
  val tmp = a[i]
  a[i] = a[j]
  a[j] = tmp
}

fun qs(a: Array<Meeting>, l: Int, r: Int) {
  if (l >= r) return
  val piv = (l + r) shr 1
  val comp = a[piv].e
  swap(a, piv, r)

  var m = l
  for (i in l until r) if (a[i].e < comp) swap(a, m++, i)
  if (comp < a[m].e) swap(a, m, r)

  qs(a, l, m - 1)
  qs(a, m + 1, r)
}

/**
6
90 120 40
50 80 70
110 140 50
10 40 80
30 60 60
70 100 100
 */

//    println("[$dep][${i}] $sum vs best=$best")
