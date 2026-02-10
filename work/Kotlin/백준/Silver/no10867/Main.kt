package 백준.Silver.no10867

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 19
const val OBS = 1 shl 13
val O = BufferedOutputStream(System.`out`, OBS)
val I = BufferedInputStream(System.`in`)
val IB = ByteArray(IBS)
var Ii = 0
var Il = 0
const val EOF = -1

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

fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

const val WS = 10
val WB = ByteArray(WS + 1).also { it[WS] = 32 }
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

const val HALF = 1_000
fun main() {
  val N = i()
  val size = HALF * 2 + 1
  val a = BooleanArray(size)
  repeat(N) {
    a[i() + HALF] = true
  }

  repeat(size) {
    if (a[it]) w(it - HALF)
  }

  O.flush()
}

/**
IN
c  cc cc    ccc
c cc
OUT
1

IN
> c  c     cc
>  cc
OUT
1

IN
aab
ab
OUT
1

IN
aaab
ab
OUT
1


IN
aaaaaaaaaaaab
aaaaaaaab
OUT
1

IN
ababc
abc
OUT
1

IN
abababababababababc
ababababababababc
OUT
1
 */
