package 백준.Gold.no1394

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 18
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

fun read(map: IntArray): Int {
  var len = 0
  var c: Byte
  while (r().also { c = it } >= 10.toByte()) {
    if (c == 10.toByte()) break
    map[c.toInt()] = 1 + len++
  }
  return len
}

fun read(buf: ByteArray): Int {
  var len = 0
  var c: Byte
  while (r().also { c = it } >= 10.toByte()) {
    if (c == 10.toByte()) break
    buf[len++.toInt()] = c
  }
  return len
}

const val CAP = 900_528
const val PW_MAX = 1_000_000
const val SIGNED_ASCII_MAX = 127

fun main() {
  val cands = IntArray(SIGNED_ASCII_MAX + 1)
  val candLen = read(cands)

  val pw = ByteArray(PW_MAX)
  val pwLen = read(pw)

  var total = 0
  var N = 1
  repeat(pwLen) { i ->
    val pi = pwLen - i - 1
    val pos = cands[pw[pi].toInt()]
    if (pos == 0) {
      w(0)
      O.flush()
      return
    }

    val cnt = (pos * N) % CAP
    total = (total + cnt) % CAP
    N = (N * candLen) % CAP
  }

  w(total)
  O.flush()
}

// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
fun getSumOfGeoSeq(
  base: Long,
  exp: Long,
  CAP: Long
): Long {
  if (exp == 0L) return 0
  if (exp == 1L) return base % CAP
  return (when {
    exp % 2 == 0L -> getSumOfGeoSeq(base, exp / 2, CAP) * (1 + pow(base, exp / 2, CAP))
    else -> getSumOfGeoSeq(base, exp - 1, CAP) + pow(base, exp, CAP)
  }) % CAP
}

fun pow(
  base: Long,
  exp: Long,
  CAP: Long
): Long {

  var r = 1L
  var b = base.toLong()
  var e = exp.toLong()

  while (e > 0) {
    if (e % 2 == 1L) r = (r * b) % CAP
    b = (b * b) % CAP
    e /= 2
  }
  return r
}

// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
// println("[$N] ${toChar(char)} $pos, cnt = $cnt")
// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
//var viewer = ByteArray(pwLen)
//var cnt = 0
//fun testDfs(
//  dep: Int,
//  viewer: ByteArray,
//  str: ByteArray,
//) {
//  if (dep == viewer.size) {
//    repeat(viewer.size) {
//      print(toChar(viewer[it]))
//    }
//    print("(${++cnt})\n")
//    return
//  }
//  repeat(str.size) {
//    val b = str[it]
//    if (b == 0.toByte()) return@repeat
//    viewer[dep] = b
//    testDfs(dep + 1, viewer, str)
//  }
//}
//testDfs(0, viewer, cands)
//println("1차= $total")
// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
