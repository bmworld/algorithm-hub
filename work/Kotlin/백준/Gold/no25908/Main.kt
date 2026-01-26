package 백준.Gold.no25908

import util.Timer
import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 5
const val OBS = 1 shl 5
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
  var c: Byte
  while (r().also { c = it } in NUM || c == 45.toByte()) {
    when (c) {
      in NUM -> v = v * 10 + c - 48
      else -> s = -1
    }
  }
  return s * v
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

const val ODD_W = -1
const val EVEN_W = 1
const val PRIME_W = ODD_W * 2
const val EMPTY = Int.MIN_VALUE

fun main() {
  val stt = i()
  val end = i()
  if (end <= 1) {
    w(ODD_W)
    O.flush()
    return
  }

  var answer = 0
  val primes = BooleanArray(end + 1) { true }.also {
    it[0] = false
    it[1] = false
    var d = 2
    while (d * d <= end) {
      for (v in d * d..end step d) it[v] = false
      d++
    }
  }

  val a = IntArray(end + 1) { EMPTY }
  a[1] = ODD_W
  a[2] = 0

  repeat(end) { i ->
    val v = 1 + i
    val r = when {
      v > 2 && primes[v] -> PRIME_W
      v.countOneBits() == 1 -> {
        val w = a[v / 2]
        if (w != EMPTY) w + 1 else v.countTrailingZeroBits() - 1
      }
      (v - 2) % 4 == 0 -> 0
      v % 2 == 0 -> {
        val (rmn, exp) = calcExponent(v, 2)
        (exp - 1) * a[rmn] * ODD_W
      }
      else -> {

        val d = getMinDivisor(v)
        val (rmn, exp) = calcExponent(v, d)
        (exp + 1) * a[rmn]
      }
    }
    a[v] = r
    if (v >= stt) answer += r
  }

  w(answer)

  O.flush()
}

fun calcExponent(
  num: Int,
  base: Int
): Pair<Int, Int> {
  var exp = 0
  var x = num
  while (x > 1 && x % base == 0) {
    exp++
    x /= base
  }

  return Pair(x, exp)
}

fun getMinDivisor(num: Int): Int {
  var dvsr = 2
  while (dvsr * dvsr <= num) {
    if (num % dvsr == 0) return dvsr
    dvsr++
  }
  return num
}

// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
// ---------------------------------------------------------------------

fun test(
  stt: Int,
  end: Int
) {

  val T = Timer()

  var total = 0
  repeat(end - stt + 1) { i ->
    val v = stt + i
    val w = getW(intArrayOf(), v)
    total += w
  }

  w(total)
  O.flush()

  T.stop()
}

fun getW(
  a: IntArray?,
  num: Int
): Int {
  if (num == 1) return ODD_W
  if (a != null) {
    val pw = a[num]
    if (pw != EMPTY) return pw
  }

  var v = num

  var ePow = 0
  var oCnt = 1

  var dvsr = 2
  while (v > 1 && dvsr <= v) {
    var cnt = 0
    while (v % dvsr == 0) {
      v /= dvsr
      cnt++
    }

    if (cnt > 0) {
      if (dvsr % 2 == 0) ePow += cnt
      else oCnt *= cnt + 1
    }
    dvsr++
  }

  var eCnt = maxOf(1, oCnt) * ePow
  val w = EVEN_W * eCnt + ODD_W * oCnt

  return w.also {
    if (a != null) a[num] = it
  }
}

fun getValidRemainder(
  num: Int,
  divisor: Int,
  evenExponent: Int,
  oddExponent: Int
): Int {
  var rmn = num shr evenExponent
  if (divisor % 2 != 0) {
    repeat(oddExponent) {
      rmn = rmn / divisor
    }
  }
  return rmn
}

//    println("-- a[$v] = ${a[v]}")
//  println("(eExp=$eExp, oExp=$oExp, rmn= $rmn) -> $pw + $w * $delta")

// 점검 (짝수, 홀수):
// 496584 (8*27*121*19): 72, 24
// 62073 (27*121*19): 0, 24
