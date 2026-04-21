package 백준.Gold.no4233

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 4
const val OBS = 1 shl 9
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
  var b: Byte
  while (r().also { b = it } in NUM) v = v * 10 + b - 48
  return v
}

val NO = "no\n".toByteArray()
val YES = "yes\n".toByteArray()

fun main() {
  while (true) {
    val p = i()
    val a = i()
    if (a == 0 || p == 0) break
    O.write(if (!isPrimeBy6k(p) && modPow(a, p, p) == a) YES else NO)
  }
  O.flush()
}

fun isPrimeBy6k(n: Int): Boolean {
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

fun modPow(base: Int, exp: Int, p: Int): Int {
  var r = 1L
  val mod = p
  var b = (base % p).toLong()
  var e = exp.toLong()
  while (e > 0L) {
    if ((e and 1L) == 1L) r = (r * b) % mod
    b = (b * b) % mod
    e = e shr 1
  }

  return r.toInt()
}

//val primeWitnesses = intArrayOf(2, 7, 61)
//fun millerRabin(d: Int, s: Int, p: Int): Boolean { // p-1 = d (odd) * 2^s
//  for (prime in primeWitnesses) {
//    if (prime >= p) continue
//    if (prime % p == 0) return true
//    if (!checkByPrimeWitness(prime, d, s, p)) return false
//  }
//  return true
//}
//
//fun checkByPrimeWitness(primeWitness: Int, d: Int, s: Int, p: Int): Boolean {
//  var r = modPow(primeWitness, d, p)
//  if (r == 1L || r == p - 1L) return true
//
//  repeat(s - 1) {
//    r = (r * r) % p
//    if (r == p - 1L) return true
//  }
//
//  return false
//}

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
