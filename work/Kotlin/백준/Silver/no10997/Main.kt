package 백준.Silver.no10997

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 8
private const val OBS = 1 shl 18
private val O = BufferedOutputStream(System.out, OBS)
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

private const val NL: Byte = 10
private const val STAR: Byte = 42
private const val SPACE: Byte = 32
private val dr = intArrayOf(0, 1, 0, -1)
private val dc = intArrayOf(-1, 0, 1, 0)
private const val DIR_CAP = 4
private const val LEFT = 0
private const val DOWN = 1
private const val RIGHT = 2
private const val UP = 3
private val dirs = intArrayOf(LEFT, DOWN, RIGHT, UP)

fun main() {
  val n = i()
  if (n == 1) {
    O.write(42)
    O.flush()
    return
  }

  val cSize = (n - 1) * 4 + 1
  val rSize = cSize + 2
  val CAP = cSize + 1
  val board = ByteArray(rSize * CAP) {
    val EOF = it % CAP == CAP - 1
    if (EOF) NL else SPACE
  }

  fun needToChangeDir(
    r: Int,
    c: Int,
    dir: Int,
  ) = when (dir) {
    LEFT -> {
      val target = c - 2
      val limit = 0
      c <= limit || target >= limit && board[encodePos(r, target, CAP)] == STAR
    }

    DOWN -> {
      val target = r + 2
      val limit = rSize - 1
      r >= limit || target <= limit && board[encodePos(target, c, CAP)] == STAR
    }

    RIGHT -> {
      val target = c + 2
      val limit = cSize - 1
      c >= limit || target <= limit && board[encodePos(r, target, CAP)] == STAR
    }

    UP -> {
      val target = r - 2
      val limit = 0
      r <= limit || target >= limit && board[encodePos(target, c, CAP)] == STAR
    }

    else -> throw Exception()
  }

  fun move(
    r: Int,
    c: Int,
    dir: Int,
  ) {
    for (d in dirs) {
      if (dir != d) continue
      val changeDir = needToChangeDir(r, c, dir)
      val nd = if (changeDir) nextDir(dir) else dir
      val nr = r + dr[nd]
      val nc = c + dc[nd]
      board[encodePos(r, c, CAP)] = STAR
      val end = changeDir && needToChangeDir(r, c, nd)
      if (!end) move(nr, nc, nd)
      break
    }
  }

  move(0, cSize - 1, LEFT)
  O.write(board)
  O.flush()
}

private fun encodePos(
  r: Int,
  c: Int,
  CAP: Int,
): Int = r * CAP + c

private fun nextDir(dir: Int): Int = (dir + 1) % DIR_CAP

// println("--- $r, $c (${printDir(dir)}) -> $nr, $nc (${printDir(nd)})")
fun printDir(dir: Int) = when (dir) {
  LEFT -> "L"
  DOWN -> "D"
  RIGHT -> "R"
  else -> "U"
}
