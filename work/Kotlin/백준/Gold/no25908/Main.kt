package 백준.Gold.no25908

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 5
private const val OBS = 1 shl 5
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = BufferedInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0
private const val EOF = -1

private fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

private val NUM = 48..57
private fun i(): Int {
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

private const val WS = 10

private val WB = ByteArray(WS + 1).also { it[WS] = 32 }
private fun w(
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

private const val ODD_W = -1
private const val EVEN_W = 1
private const val PRIME_W = ODD_W * 2
private const val EMPTY = Int.MIN_VALUE

fun main() {
  val stt = i()
  val end = i()
  if (end <= 1) {
    w(ODD_W)
    O.flush()
    return
  }

  var answer = 0
  val a = IntArray(end + 1) { EMPTY }
  a[1] = ODD_W
  a[2] = 0

  repeat(end - stt + 1) { i ->
    val v = stt + i
    val w = a[v]
    val r = when {
      w != EMPTY -> w
      v.countOneBits() == 1 -> {
        val w = a[v / 2]
        if (w != EMPTY) w + 1 else v.countTrailingZeroBits() - 1
      }
      (v - 2) % 4 == 0 -> 0
      else -> {
        val d = getMinDivisor(v)
        if (d == v) PRIME_W else {
          val eExp = getExponent(v, 2)
          val oExp = if (d != 2) getExponent(v, d) else 1
          var rmn = getValidRemainder(v, d, eExp, oExp)
          val delta = if (d % 2 == 0) -1 else 1
          val pw = getW(a, v / d)
          val w = getW(a, rmn)
          pw + w * delta
        }
      }
    }

    a[v] = r
    answer += r
  }

  w(answer)
  O.flush()
}

private fun getValidRemainder(
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

fun getExponent(
  num: Int,
  base: Int
): Int {
  var exp = 0
  var x = num
  while (x > 1 && x % base == 0) {
    exp++
    x /= base
  }
  return exp
}

private fun getMinDivisor(num: Int): Int {
  var dvsr = 2
  while (dvsr * dvsr <= num) {
    if (num % dvsr == 0) return dvsr
    dvsr++
  }
  return num
}

private fun getW(
  a: IntArray,
  num: Int
): Int {
  if (num == 1) return ODD_W
  val pw = a[num]
  if (pw != EMPTY) return pw

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
    a[num] = it
  }
}

//    println("-- a[$v] = ${a[v]}")
//  println("(eExp=$eExp, oExp=$oExp, rmn= $rmn) -> $pw + $w * $delta")

// 점검 (짝수, 홀수):
// 496584 (8*27*121*19): 72, 24
// 62073 (27*121*19): 0, 24
