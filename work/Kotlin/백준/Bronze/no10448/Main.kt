package 백준.Bronze.no10448

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 10
const val OBS = 1 shl 5
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

var isValid = 0
const val MAX_T = 44
const val MAX_COMB = 3
fun main() {
  val tnum = IntArray(MAX_T + 1).also {
    for (i in 1 until it.size) it[i] = it[i - 1] + i
  }

  fun dfs(selected: Int, end: Int, sum: Int, t: Int) {
    if (isValid == 1) return
    if (selected == MAX_COMB) {
      if (sum == t) isValid = 1
      return
    }
    if (sum >= t) return

    for (i in end downTo 1) {
      dfs(selected + 1, i, sum + tnum[i], t)
    }
  }

  repeat(i()) {
    val x = i()
    val end = binSearch(x, tnum)
    dfs(0, end - if (tnum[end] == x) 1 else 0, 0, x)
    w(isValid)
    isValid = 0
    O.write(10)
  }

  O.flush()
}

fun binSearch(x: Int, arr: IntArray): Int {

  var l = 0
  var r = arr.size - 1

  while (l <= r) {
    val m = (l + r) shr 1
    val mx = arr[m]
    when {
      mx == x -> return m
      mx < x -> l = m + 1
      else -> r = m - 1
    }
  }

  return r
}
