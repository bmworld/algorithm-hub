package 백준.Silver.no6588

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 18
const val OBS = 1 shl 15
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

const val CNDS_MAX_CNT = 100_000
val EQ = "= ".toByteArray()
val PLUS = "+ ".toByteArray()
val IMPOSSIBLE = "Goldbach's conjecture is wrong.\n".toByteArray()

fun main() {
  val cnds = IntArray(CNDS_MAX_CNT)
  var cndCnt = 0
  var max = 0
  loop@ while (true) {
    val N = i() // 6 <= N <= 1_000_000
    if (N == 0) break
    cnds[cndCnt++] = N
    if (N > max) max = N
  }

  val primes = BooleanArray(max + 1) { it > 1 }.also {
    var d = 2
    while (d <= max / d) {
      for (i in d * d..max step d) it[i] = false
      d += if (d == 2) 1 else 2
    }
  }

  repeat(cndCnt) {
    val N = cnds[it]
    for (v1 in 3..N / 2 step 2) {
      val v2 = N - v1
      if (primes[v1] && primes[v2]) {
        w(N)
        O.write(EQ)
        w(v1)
        O.write(PLUS)
        w(v2, true)
        return@repeat
      }
    }
    O.write(IMPOSSIBLE)
  }

  O.flush()
}

/**
[IN]
36
1034
16
258
3874
0

[OUT]
36 = 5 + 31
1034 = 3 + 1031
16 = 3 + 13
258 = 7 + 251
3874 = 11 + 3863
 */
