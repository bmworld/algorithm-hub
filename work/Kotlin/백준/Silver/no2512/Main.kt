package 백준.Silver.no2512

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
  val bdgt = IntArray(N) { i() }
  val M = i()

  val avg = M / N
  var rmn = M % N

  var underAvgCnt = 0
  val cnds = IntArray(N)
  var ci = 0
  for (x in bdgt) when {
    x < avg -> {
      underAvgCnt++
      rmn += avg - x
    }
    x > avg -> cnds[ci++] = x
  }

  val max = avg + if (N != underAvgCnt) rmn / (N - underAvgCnt) else 0
  var cap = avg
  repeat(ci) {
    val x = cnds[it]
    if (x in cap..max) cap = x
    else cap = max
  }

  w(cap)
  O.flush()
}

/**
IN
4
6 10 1 10
37

OUT
10



IN
4
4 1 4 2
16

OUT
4
 */

//  println("avg = ${avg}, rmn=$rmn, under=$underAvgCnt, max=$cap, cap=$max")
