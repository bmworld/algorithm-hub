package 백준.Gold.no13172

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 16
const val OBS = 1 shl 8
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

const val WS = 20
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

const val MOD = 1_000_000_007
fun main() {

  fun pow(base: Int, exp: Int): Long {
    var v = 1L
    var a = base.toLong()
    var b = exp
    while (b > 0) {
      if (b % 2 == 1) v = (v * a) % MOD
      a = (a * a) % MOD
      b /= 2
    }

    return v
  }

  fun divideConquer(base: Int, exp: Int): Long {
    val half = pow(base, exp / 2)
    val sqrd = (half * half) % MOD
    return (sqrd * if (exp % 2 != 0) base else 1) % MOD
  }

  var sigma = 0L
  repeat(i()) {
    val n = i()
    val s = i()
    val inverseN = divideConquer(n, MOD - 2)

    var q = s * inverseN
    if (q >= MOD) q %= MOD
    var ns = sigma + q
    if (ns >= MOD) ns %= MOD
    sigma = ns
  }

  w(sigma)
  O.flush()
}

/**
// ---------------------------------------------------------------------
페르마의 소정리의 역원으로 항만들기: a/b = a * (b^MOD-2)
👉 연산방법
1. 덧셈, 뺄셈, 곱셈: mod X에서 해당 연산
2. 나눗셈: 나누는 수의 역원 구하고, 그 역원을 mod X 에서 곱셈
✅ a/b = [ 정수 or 기약분수 ] 인 경우 모두 유효하게 계산됨
// ---------------------------------------------------------------------


IN
1
692 28
OUT
543352605

IN
2
954 83
17 78
OUT
12640281
 */

//println("n=$n, s=$s -> sigma=$sigma ($s * $inverseN)")
