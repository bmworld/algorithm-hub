package 백준.Silver.no1004

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 10
const val OBS = 1 shl 6
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
val WB = ByteArray(WS + 1).also { it[WS] = 10 }
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
  O.write(WB, ++pos, WS - pos + 1)
}

fun main() {

  repeat(i()) {
    var cnt = 0
    val x1 = i()
    val y1 = i()
    val x2 = i()
    val y2 = i()
    repeat(i()) {
      val cx = i()
      val cy = i()
      val r = i()
      val rr = r * r
      val d1 = sqr(x1 - cx) + sqr(y1 - cy)
      val d2 = sqr(x2 - cx) + sqr(y2 - cy)
      if (rr in d1 until d2 || rr in d2 until d1) cnt++
    }
    w(cnt)
  }
  O.flush()
}

fun sqr(v: Int): Int = v * v

/**
IN
1
225 -122 -157 865
20
954 -85 403
990 -25 648
-399 503 732
-344 -457 114
-657 434 997
-122 -598 604
315 371 347
965 142 137
78 147 935
-535 111 798
-172 386 215
312 230 245
-803 -593 720
373 -955 662
764 935 289
337 -337 725
644 -228 167
-393 -667 356
789 148 742
168 177 721

OUT
7
 */
