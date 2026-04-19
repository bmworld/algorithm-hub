package 백준.Silver.no17103

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 10
const val OBS = 1 shl 10
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


const val MAX_CASE = 100

fun main() {
  val cnds = IntArray(MAX_CASE)
  val case = i()
  var max = 0
  repeat(case) {
    val N = i() // 2 < N <= 1_000_000
    cnds[it] = N
    if (N > max) max = N
  }

  val primes = BooleanArray(max + 1) { it > 1 }.also {
    var d = 2
    while (d <= max / d) {
      for (i in d * d..max step d) it[i] = false
      d += if (d == 2) 1 else 2
    }
  }

  repeat(case) {
    val N = cnds[it]
    var cnt = 0
    if (primes[2] && primes[N - 2]) cnt++
    for (n in 3..N / 2 step 2) if (primes[n] && primes[N - n]) cnt++
    w(cnt)
  }

  O.flush()
}

/**
[IN]
6
4
6
8
10
12
100
[OUT]
1
1
1
2
1
6
 */
