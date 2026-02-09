package 백준.Gold.no1030

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 8
const val OBS = 1 shl 15
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

const val NL: Byte = 10
const val WH: Byte = 48
const val BK: Byte = 49

fun main() {
  val GOAL = i()
  val N = i()
  val K = i()
  val r1 = i()
  val r2 = i()
  val c1 = i()
  val c2 = i()
  val H = r2 - r1 + 1
  val W = c2 - c1 + 1
  val CAP = W + 1

  val canvas = ByteArray(CAP * H) {
    if (it % CAP == CAP - 1) NL else WH
  }

  fun pos(r: Int, c: Int): Int = (r - r1) * CAP + (c - c1)
  fun inRange(r: Int, c: Int) = r in r1..r2 && c in c1..c2


  val kStt = (N - K) shr 1
  val kEnd = N - 1 - kStt

  fun useBK(ri: Int, ci: Int, color: Byte): Boolean =
    color == BK || ri in kStt..kEnd && ci in kStt..kEnd

  fun rcsv(dep: Int, size: Int, r: Int, c: Int, color: Byte) {
    if (dep > GOAL) return

    repeat(N) { ri ->
      repeat(N) { ci ->
        var tr = r + ri
        var tc = c + ci
        if (inRange(tr, tc) && (useBK(ri, ci, color))) canvas[pos(tr, tc)] = BK
      }
    }

    val nxtSize = size * N
    repeat(N) { ri ->
      repeat(N) { ci ->
        var nr = r + ri * size
        var nc = c + ci * size
        rcsv(dep + 1, nxtSize, nr, nc, if (useBK(ri, ci, color)) BK else WH)
      }
    }
  }

  rcsv(1, N, 0, 0, WH)
  O.write(canvas)
  O.flush()
}

//println("--next [$dep][size=$size] $r, $c -> $nr, $nc ($color)")
//        println("[painted] [$dep][size=$size] $r, $c -> $tr, $tc ($color)")
//println("[BK RANGE] ($N - $K / 2) in $kStt .. $kEnd ")
