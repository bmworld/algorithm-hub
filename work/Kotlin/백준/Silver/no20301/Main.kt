package 백준.Silver.no20301

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 4
const val OBS = 1 shl 16
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
val WB = ByteArray(WS + 1).also { it[WS] = 10 }
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

fun main() {
  val N = i()
  val K = i()
  val M = i()
  val a = mutableListOf<Int>().also {
    repeat(N) { i ->
      it.add(i + 1)
    }
  }

  var pos = 0
  var len = N
  val dist = K - 1
  var reverse = false
  var cycle = 0
  repeat(N) {
    val delta = if (reverse) -dist else dist
    var np = move(pos + delta, len)

    w(a.removeAt(np))

    cycle = if (cycle + 1 < M) cycle + 1 else {
      reverse = !reverse
      0
    }

    if (reverse) np--
    pos = move(np, --len)
  }
  O.flush()
}

fun move(pos: Int, len: Int): Int {
  if (len == 0) return pos
  var x = pos
  when {
    x >= len -> x %= len
    x < 0 -> {
      val rmn = x % len
      x = if (rmn == 0) 0 else len + rmn
    }
  }
  return x
}

/**
IN
7 3 4
OUT
3 6 2 7 1 5 4

IN
5 2 1
OUT
2 5 3 4 1
 */

//println("[${if (reverse) "R" else "S"} $len] $pos ($delta) -> $np = ${a[np]} / len=$len")
