package 백준.Silver.no1940

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 18
const val OBS = 1 shl 4
val O = BufferedOutputStream(System.out, OBS)
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
  var N = i()
  val M = i()
  var validCnt = 0
  val a = IntArray(N)
  repeat(N) {
    val v = i()
    if (v < M) a[validCnt++] = v
  }
  N = validCnt

  fun swap(
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun sort(
    l: Int,
    r: Int,
  ): Int {
    val m = (l + r) shr 1
    val mv = a[m]
    swap(m, r)

    var pos = l
    for (i in l until r) if (a[i] < mv) swap(pos++, i)
    if (mv < a[pos]) swap(pos, r)
    return pos
  }

  fun qs(
    l: Int,
    r: Int,
  ) {
    if (l >= r) return
    val m = sort(l, r)
    qs(l, m - 1)
    qs(m + 1, r)
  }

  qs(0, N - 1)

  var ans = 0
  var l = 0
  var r = N - 1
  while (l < r) {
    val sum = a[l] + a[r]
    when {
      sum > M -> r--
      sum < M -> l++
      else -> {
        ans++
        l++
        r--
      }
    }
  }

  w(ans)
  O.flush()
}
