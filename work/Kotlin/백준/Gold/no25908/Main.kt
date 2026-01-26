package 백준.Gold.no25908

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
val WB = ByteArray(WS)
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
  O.write(WB, ++pos, WS - pos)
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

  val minDvsrs = IntArray(end + 1) { it }.also {
    var d = 2
    while (d * d <= end) {
      for (v in d * d..end step d) if (it[v] == v) it[v] = d
      d++
    }
  }

  val a = IntArray(end + 1) { EMPTY }
  var answer = 0
  repeat(end) { i ->
    val v = 1 + i
    val d = minDvsrs[v]
    val r = when {
      v.countOneBits() == 1 -> v.countTrailingZeroBits() - 1
      v % 4 == 2 -> 0
      v > 1 && v == d -> PRIME_W
      else -> {
        var exp = 0
        var rmn = v
        var base = if (v % 2 == 0) 2 else d
        while (rmn > 1 && rmn % base == 0) {
          exp++
          rmn /= base
        }
        a[rmn] * if (v % 2 == 0) (exp - 1) * ODD_W else (exp + 1)
      }
    }
    a[v] = r
    if (v >= stt) answer += r
  }

  w(answer)
  O.flush()
}

// ---------------------------------------------------------------------
// ---------------------------------------------------------------------
// ---------------------------------------------------------------------

fun test(
  stt: Int,
  end: Int
) {

  //  val T = Timer()

  var total = 0
  repeat(end - stt + 1) { i ->
    val v = stt + i
    val w = getW(intArrayOf(), v)
    total += w
  }

  w(total)
  O.flush()

  //  T.stop()
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

//     println("a[$v] = ${a[v]}, ${getW(null, v)}")

// 점검 (짝수, 홀수):
// 496584 (8*27*121*19): 72, 24
// 62073 (27*121*19): 0, 24
