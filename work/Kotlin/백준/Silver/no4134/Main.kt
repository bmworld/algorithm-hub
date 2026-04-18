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

val primeValidators = longArrayOf(2, 7, 61)
fun main() {
  repeat(i().toInt()) {
    var v = i()
    loop@ while (true) {
      for (prime in primeValidators) {
        if (v == prime) break@loop
        val isPrime = isPrimeByMillerRabin(prime, v)
        if (!isPrime) {
          v += if (v % 2 == 0L) 1 else 2
          continue@loop
        }
      }
      break
    }

    w(v)
  }
  O.flush()
}

fun isPrimeByMillerRabin(base: Long, num: Long): Boolean {
  if (num % base == 0L) return false

  var exp = num - 1
  while (true) {
    val r = modPow(base, exp, num)
    if (r == num - 1) return true
    if (exp % 2 == 1L) return (r == 1L || r == num - 1)
    exp /= 2
  }
}

fun modPow(base: Long, exp: Long, mod: Long): Long {
  var r = 1L

  var b = base % mod
  var e = exp
  while (e > 0) {
    if (e % 2 == 1L) r = multiply(r, b, mod)
    b = multiply(b, b, mod)
    e /= 2
  }

  return r
}

fun multiply(n1: Long, n2: Long, mod: Long): Long {
  var r = 0L

  var n = n1 % mod
  var times = n2 % mod
  while (times > 0) {
    if (times % 2 == 1L) r = (r + n) % mod
    n = (2 * n) % mod
    times /= 2
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
