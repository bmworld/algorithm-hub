package 백준.Silver.no2193

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 5
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
  var c: Byte
  while (r().also { c = it } in NUM || c == 45.toByte()) {
    when (c) {
      in NUM -> v = v * 10 + c - 48
      else -> s = -1
    }
  }
  return s * v
}

const val WS = 30
val WB = ByteArray(WS)
fun w(
  num: Long
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
  val a = LongArray(N + 1)
  val acc = LongArray(N + 1)
  repeat(N) {
    var v = 1 + it
    val cnt = 1 + if (v >= 2) acc[v - 2] else 0
    a[v] = cnt
    acc[v] = acc[v - 1] + cnt
  }
  w(a[N])
  O.flush()
}

// ---------------------------------------------------------------------
//private fun test(N: Int) {
//  var cnt = 0
//  val NUM = ByteArray(N)
//  fun dfs(
//    dep: Int,
//  ) {
//    if (dep == N) {
//      O.write("[TEST]".toByteArray())
//      O.write(NUM, 0, N)
//      O.write(10)
//      cnt++
//      return
//    }
//
//    repeat(2) { v ->
//      if (dep == 0 && v == 0) return@repeat
//      if (dep > 0 && NUM[dep - 1] == 49.toByte() && v == 1) return@repeat
//      NUM[dep] = (v + 48).toByte()
//      dfs(dep + 1)
//    }
//  }
//  dfs(0)
//  println(cnt)
//}
//println("a[$v] = ${a[v]} / acc= ${acc[v]}")
