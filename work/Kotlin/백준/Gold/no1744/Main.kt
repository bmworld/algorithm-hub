package 백준.Gold.no1744

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 8
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

const val MAX = 1_000
fun main() {

  val n = i()
  val cnts = IntArray(MAX * 2 + 1)
  var minI = Int.MAX_VALUE
  var maxI = Int.MIN_VALUE
  repeat(n) {
    val i = encodeNum(i())
    cnts[i]++
    if (i < minI) minI = i
    if (i > maxI) maxI = i
  }

  var ans = 0

  var b = Int.MAX_VALUE
  for (i in minI..minOf(maxI, encodeNum(-1))) {
    val a = decodeIdx(i)

    var cnt = cnts[i]
    if (cnt == 0) continue

    if (b < 0) {
      cnt--
      ans += a * b
      b = Int.MAX_VALUE
    }

    ans += (a * a) * (cnt / 2)
    if (cnt % 2 != 0) b = a
  }

  if (b < 0 && cnts[encodeNum(0)] == 0) ans += b


  b = Int.MIN_VALUE
  for (i in maxI downTo maxOf(minI, encodeNum(2))) {
    val a = decodeIdx(i)

    var cnt = cnts[i]
    if (cnt == 0) continue

    if (b > 0) {
      cnt--
      ans += a * b
      b = Int.MIN_VALUE
    }

    ans += (a * a) * (cnt / 2)
    if (cnt % 2 != 0) b = a
  }

  if (b > 0) ans += b
  ans += cnts[encodeNum(1)]

  w(ans)
  O.flush()
}

fun encodeNum(num: Int) = num + MAX
fun decodeIdx(idx: Int) = idx - MAX

/**
[IN]
2
1
9
[OUT]
10

[IN]
3
-1
0
1
[OUT]
1

[IN]
2
-1
0
[OUT]
0

[IN]
3
-1
0
1
[OUT]
1
 */
