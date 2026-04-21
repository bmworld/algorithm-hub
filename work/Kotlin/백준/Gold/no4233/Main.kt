package 백준.Gold.no4233

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 4
const val OBS = 1 shl 12
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
  var b: Byte
  while (r().also { b = it } in NUM) v = v * 10 + b - 48
  return v
}

val NO = "no\n".toByteArray()
val YES = "yes\n".toByteArray()
val primeWitnesses = longArrayOf(2, 7, 61)

fun main() {
  while (true) {
    val p = i()
    val a = i()
    if (a == 0L || p == 0L) break
    O.write(if (isFakePrime(a, p)) YES else NO)
  }
  O.flush()
}

fun isFakePrime(a: Long, p: Long): Boolean {

  var d = p - 1
  var s = 0
  while (d % 2 == 0L) {
    d /= 2
    s++
  }

  var isPrime = when {
    p < 2 || p % 2 == 0L -> false
    p == 3L -> true
    else -> millerRabin(d, s, p)
  }

  return !isPrime && fermat(a, p)
}

fun fermat(a: Long, p: Long): Boolean = modPow(a, p, p) == a.toULong()

fun millerRabin(d: Long, s: Int, p: Long): Boolean { // p-1 = d (odd) * 2^s
  for (prime in primeWitnesses) {
    if (prime >= p) continue
    if (prime % p == 0L) return true
    if (!checkByPrimeWitness(prime, d, s, p)) return false
  }
  return true
}

fun checkByPrimeWitness(primeWitness: Long, d: Long, s: Int, p: Long): Boolean {
  var r = modPow(primeWitness, d, p)
  val mod = p.toULong()
  if (r == 1UL || r == mod - 1UL) return true

  repeat(s - 1) {
    r = (r * r) % mod
    if (r == mod - 1UL) return true
  }

  return false
}

fun modPow(base: Long, exp: Long, p: Long): ULong {
  var r = 1UL
  val mod = p.toULong()
  var b = (base % p).toULong()
  var e = exp.toULong()
  while (e > 0UL) {
    if ((e and 1UL) == 1UL) r = (r * b) % mod
    b = (b * b) % mod
    e = e shr 1
  }

  return r
}

//fun getGCD(a: Long, b: Long): Long = if (b == 0L) a else getGCD(b, a % b)

/**
[IN]
6 3
2047 2
999999937 819033270
45 27
1105 716
8911 2
7 3
34 9
2465 1421
10585 1300
561 66
6601 1104
0 0

[OUT]
yes
yes
no
yes
yes
yes
no
no
yes
yes
yes
yes
 */

//   println("[$a, $p] isPrime = ${isPrime}, ${fermat(a, p)}")
