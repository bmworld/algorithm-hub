package 백준.Silver.no4134

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
fun i(): Long {
  var v = 0L
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
val WB = ByteArray(WS + 1).also { it[WS] = 10 }
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
  O.write(WB, ++pos, WS - pos + 1)
}

val primeWitnesses = longArrayOf(2, 7, 61)

fun main() {
  repeat(i().toInt()) {
    var v = i()
    if (v <= 2) {
      w(2)
      return@repeat
    }

    if (v % 2L == 0L) v++
    while (!isPrime(v)) v += 2

    w(v)
  }
  O.flush()
}

fun isPrime(num: Long): Boolean {
  if (num < 2) return false
  if (num <= 3) return true
  if (num % 2 == 0L) return false
  for (prime in primeWitnesses) {
    if (prime == num) return true
    if (!checkByMillerRabin(prime, num)) return false
  }

  return true
}

fun checkByMillerRabin(base: Long, num: Long): Boolean { // n-1 = d * 2^s

  var d = num - 1
  var s = 0
  while (d % 2 == 0L) {
    d /= 2
    s++
  }

  var r = modPow(base, d, num)
  if (r == 1L || r == num - 1) return true

  repeat(s - 1) {
    r = modMul(r, r, num)
    if (r == num - 1) return true
  }

  return false
}

fun modPow(base: Long, exp: Long, mod: Long): Long {
  var r = 1L

  var b = base % mod
  var e = exp
  while (e > 0) {
    if ((e and 1L) == 1L) r = modMul(r, b, mod)
    b = modMul(b, b, mod)
    e = e shr 1
  }

  return r
}

fun modMul(n1: Long, n2: Long, mod: Long): Long {
  var r = 0L

  var a = n1 % mod
  var b = n2

  while (b > 0) {
    if ((b and 1L) == 1L) r = (r + a) % mod
    a = (a shl 1) % mod
    b = b shr 1
  }

  return r
}

/**
 * # Prime Validators
 * Int : [2, 7, 61]
 * Long: [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31]
 *
 *
 * # 소수 mod 세계에서
 * -> x^2 ≡ 1 (mod p)
 * 👉 x = ±1 (mod p)
 *    · (+1)^2 = 1
 *    · (-1)^2 = 1
 * 👉 여기서 x = -1 (mod p) 만족하는 x 는 `p-1` 이다.
 * e.g.) mod 7
 * 1^2 = 1
 * -> +1 (mod p) 만족하는 x는 1
 * 6^2 = 36 ≡ 1
 * -> +1 (mod p) 만족하는 x는 6
 */

/**
IN
10
0
1
2
3
4
14
20
100
3999999999
4000000000

OUT
2
2
2
3
5
17
23
101
4000000007
4000000007
 */
