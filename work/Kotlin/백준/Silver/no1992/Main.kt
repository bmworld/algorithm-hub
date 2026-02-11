package 백준.Silver.no1992

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 10
const val OBS = 1 shl 10
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

fun b(): Byte {
  var b: Byte
  while (r().also { b = it } >= 10) if (b in WH..BK) return b
  return 0
}

const val WH: Byte = 48
const val BK: Byte = 49
const val MIXED: Byte = -1
const val OPEN: Byte = 40
const val CLOSE: Byte = 41
const val QUAD = 4
fun main() {
  val N = i()
  val size = N * N
  val map = ByteArray(size) { b() }
  val zip = ByteArray(size * 2)
  var len = 0
  fun pos(r: Int, c: Int): Int = r * N + c

  fun merge() {
    val target = len - QUAD
    zip[target - 1] = zip[target]
    len -= QUAD
  }

  fun rcs(size: Int, r: Int, c: Int): Byte {
    if (size == 1) return map[pos(r, c)].also {
      zip[len++] = it
    }

    zip[len++] = OPEN
    val half = size shr 1
    val q1 = rcs(half, r, c)
    val q2 = rcs(half, r, c + half)
    val q3 = rcs(half, r + half, c)
    val q4 = rcs(half, r + half, c + half)

    val color = if (q1 == q2 && q2 == q3 && q3 == q4) q1 else MIXED
    return color.also {
      if (it == MIXED) zip[len++] = CLOSE else merge()
    }
  }

  rcs(N, 0, 0)
  O.write(zip, 0, len)
  O.flush()
}

/**
IN
8
11101101
11011110
01010100
00110010
00000000
00001010
00101010
00000100

OUT (AC)
((1(1001)(0100)(0111))(1(0110)(0100)(0010))(000(1000))((0010)(0010)(1001)(1000)))

->
(
(1(1001)(0100)(0111))
(1(0110)(0100)(0010))
(000(1000))
((0010)(0010)(1001)(1000))
)

OUT (WA)
(((((0(1110)(0110)(1100))(0011)11(1011))(1001)0(0110)(0111))(1101)(0011)0(0110)))

IN
8
00110011
00101111
01111110
10001111
10001100
01000111
01010001
10110010

OUT
((0(1110)(0110)(1100))((0011)11(1011))((1001)0(0110)(0111))((1101)(0011)0(0110)))
->
(
(0(1110)(0110)(1100))
((0011)11(1011))
((1001)0(0110)(0111))
((1101)(0011)0(0110))
)

 */
