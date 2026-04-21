package 백준.Gold.no1990

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 20
const val OBS = 6_000
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

const val NL = 10
const val ZERO: Byte = 48
val NUM = ZERO..ZERO + 9
val DONE = byteArrayOf(45, 49)
val primeWitnesses = intArrayOf(2, 7, 61)

fun main() {
  var b: Byte
  var minLen = 0
  var maxLen = 0

  var fr = 0
  while (r().also { b = it } in NUM) {
    fr = fr * 10 + b - ZERO
    minLen++
  }

  var to = 0
  while (r().also { b = it } in NUM) {
    to = to * 10 + b - ZERO
    maxLen++
  }

  val NUM = ByteArray(maxLen)
  fun dfs(l: Int, len: Int) {
    if (l == (len + 1) / 2) {
      val v = getNum(len, NUM)
      if (v in fr..to && isPrime(v)) {
        O.write(NUM, 0, len)
        O.write(NL)
      }

      return
    }

    val r = len - (l + 1)
    for (n in 0..9) {
      if (l == 0 && (n == 0 || n % 2 == 0)
        || len % 2 == 0 && len != 2
      ) continue
      NUM[l] = (n + ZERO).toByte().also { if (l != r) NUM[r] = it }
      dfs(l + 1, len)
    }
  }

  for (len in minLen..maxLen) dfs(0, len)

  O.write(DONE)
  O.flush()
}

fun getNum(len: Int, arr: ByteArray): Int {
  var v = 0
  repeat(len) {
    v = v * 10 + (arr[it] - ZERO)
  }
  return v
}

fun isPrime(num: Int): Boolean { // 5 <= num < 100_000_000
//  if (num < 2) return false
//  if (num == 3) return true
//  if (num % 2 == 0) return false
  return checkByMillerRabin(num)
}

fun checkByMillerRabin(n: Int): Boolean { // n-1 = d (odd) * 2^s

  var d = n - 1
  var s = 0
  while (d % 2 == 0) {
    d /= 2
    s++
  }

  for (prime in primeWitnesses) {
    if (prime >= n) continue
    if (prime % n == 0) return true
    if (!checkByPrimeWitness(prime, d, s, n)) return false
  }

  return true
}

fun checkByPrimeWitness(primeWitness: Int, d: Int, s: Int, n: Int): Boolean {
  var r = modPow(primeWitness, d, n)
  val mod = n.toULong()
  if (r == 1UL || r == mod - 1UL) return true

  repeat(s - 1) {
    r = (r * r) % mod
    if (r == mod - 1UL) return true
  }

  return false
}

fun modPow(base: Int, exp: Int, n: Int): ULong {
  var r = 1UL
  val mod = n.toULong()
  var b = (base % n).toULong()
  var e = exp.toULong()
  while (e > 0UL) {
    if ((e and 1UL) == 1UL) r = (r * b) % mod
    b = (b * b) % mod
    e = e shr 1
  }

  return r
}
