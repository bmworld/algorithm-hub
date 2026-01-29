package 백준.Gold.no1394

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 20
const val OBS = 1 shl 3
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

fun readStr(buf: ByteArray): Int {
  var len = 0
  var c: Byte
  while (r().also { c = it } >= 10.toByte()) {
    if (c == 10.toByte()) break
    buf[len++] = c
  }
  return len
}

const val CAP = 900_528
const val CHAR_MAX = 100
const val PW_MAX = 1_000_000

fun main() {
  val cands = ByteArray(CHAR_MAX)
  val candLen = readStr(cands)

  val pw = ByteArray(PW_MAX)
  val pwLen = readStr(pw)

  var total = getSumOfGeoSeq(candLen, pwLen - 1, CAP)

  repeat(pwLen) { i ->
    val char = pw[i]
    val pos = getPos(char, cands, candLen)
    val exp = pwLen - (i + 1)
    val cnt = when {
      pos == -1 -> {
        w(0)
        O.flush()
        return
      }
      i + 1 == pwLen -> pos + 1
      pos > 0 -> pos * pow(candLen, exp, CAP)
      else -> 0
    }
    total = (total + cnt) % CAP
  }

  w(total)
  O.flush()
}

fun getPos(
  char: Byte,
  chars: ByteArray,
  len: Int
): Int {
  var pos = -1
  for (i in 0 until len) if (char == chars[i]) {
    pos = i
    break
  }
  return pos
}

fun getSumOfGeoSeq(
  base: Int,
  exp: Int,
  CAP: Int = Int.MAX_VALUE
): Int {
  if (exp == 0) return 0
  if (exp == 1) return base % CAP
  return (when {
    exp % 2 == 0 -> getSumOfGeoSeq(base, exp / 2) * (1 + pow(base, exp / 2))
    else -> getSumOfGeoSeq(base, exp - 1) + pow(base, exp)
  }) % CAP
}

fun pow(
  base: Int,
  exp: Int,
  CAP: Int = Int.MAX_VALUE
): Int {

  var r = 1L
  var b = base.toLong()
  var e = exp.toLong()

  while (e > 0) {
    if (e % 2 == 1L) r = (r * b) % CAP
    b = (b * b) % CAP
    e /= 2
  }
  return r.toInt()
}

// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
// println("[$i: ${toChar(char)}] pos=$pos cnt = $total")
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
