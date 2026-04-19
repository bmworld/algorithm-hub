package 백준.Silver.no9020

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

fun main() {

  repeat(i()) {
    val N = i()
    var v1 = N / 2
    var v2 = N - v1
    if (v1 > 2) {
      if (v1 % 2L == 0L) {
        v1++
        v2--
      }
      while (!isPrime(v1) || !isPrime(v2)) {
        v1 += 2
        v2 -= 2
        if (v2 < 2) break
      }
    }

    w(v2)
    w(v1, true)
  }
  O.flush()
}


fun isPrime(n: Int): Boolean {
  if (n < 2) return false
  if (n <= 3) return true
  if (n % 2 == 0 || n % 3 == 0) return false

  var d = 5
  while (d * d <= n) {
    if (n % d == 0 || n % (d + 2) == 0) return false
    d += 6
  }

  return true
}

/**
[IN]
5
36
1034
16
258
3874

[OUT]
17 19
487 547
5 11
127 131
1901 1973
 */
