package 백준.Silver.no2578

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 132
const val OBS = 1 shl 2
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

const val cellCnt = 5
const val BINGO = 3
const val SEP = 100
fun main() {
  val board = IntArray(cellCnt * cellCnt)
  fun pos(r: Int, c: Int): Int = r * SEP + c
  repeat(cellCnt) { r ->
    repeat(cellCnt) { c ->
      board[i() - 1] = pos(r, c)
    }
  }

  var lines = 0
  val ch = IntArray(cellCnt * 2 + 2) { cellCnt }

  fun check(pos: Int) {
    val cnt = ch[pos]
    if (cnt == 0) return
    ch[pos] = (cnt - 1).also { if (it == 0) lines++ }
  }

  for (order in 1..25) {
    val n = i() - 1
    val e = board[n]
    val r = e / SEP
    val c = e % SEP

    check(r)
    check(cellCnt + c)
    if (r == c) check(cellCnt * 2)
    if (r + c == 4) check(cellCnt * 2 + 1)
    if (lines >= BINGO) {
      w(order)
      break
    }
  }

  O.flush()
}
