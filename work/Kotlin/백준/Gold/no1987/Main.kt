package 백준.Gold.no1987

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 10
private const val OBS = 1 shl 5
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
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

private const val A: Byte = 65
private const val NL: Byte = 10
private fun b(): Int {
  var c: Byte
  var char: Byte = 0
  while (r().also { c = it } >= NL) {
    if (c >= A) {
      char = c
      break
    }
  }

  return char - A
}

private const val WS = 10
private val WB = ByteArray(WS)
private fun w(
  num: Int,
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

private val dr = intArrayOf(0, 1, 0, -1)
private val dc = intArrayOf(1, 0, -1, 0)
private const val ALPHABET_MAX = 26

fun main() {
  val rSize = i()
  val cSize = i()

  fun inRange(
    r: Int,
    c: Int,
  ) = r in 0 until rSize && c in 0 until cSize

  fun encodePos(
    r: Int,
    c: Int,
  ): Int = r * cSize + c

  fun mergeFlag(
    flag: Int,
    char: Int,
  ): Int = flag or (1 shl char)

  val chars = IntArray(rSize * cSize) { b() }
  val traced = IntArray(rSize * cSize)

  var maxCnt = 1

  fun dfs(
    r: Int,
    c: Int,
    cnt: Int,
    flag: Int,
  ) {
    val pos = encodePos(r, c)
    val visited = traced[pos] == flag
    if (visited || maxCnt >= ALPHABET_MAX) return
    traced[pos] = flag

    repeat(4) {
      val nr = dr[it] + r
      val nc = dc[it] + c
      if (!inRange(nr, nc)) return@repeat
      val char = chars[encodePos(nr, nc)]
      val contained = flag and (1 shl char) != 0
      if (contained) return@repeat
      val nextCnt = cnt + 1
      if (maxCnt < nextCnt) maxCnt = nextCnt
      dfs(nr, nc, nextCnt, mergeFlag(flag, char))
    }
  }

  dfs(0, 0, 1, mergeFlag(0, chars[encodePos(0, 0)]))

  w(maxCnt)
  O.flush()
}
