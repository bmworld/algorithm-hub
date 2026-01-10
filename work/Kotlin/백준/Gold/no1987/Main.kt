package 백준.Gold.no1987

import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.util.*

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

  val chars = IntArray(rSize * cSize) { b() }
  val q = PriorityQueue(compareBy<Node> { it.cnt }.thenBy { it.r }
    .thenBy { it.c })
  val used = Array(rSize * cSize) { mutableMapOf<Int, Boolean>() }
  val sttPos = encodePos(0, 0)
  val char = chars[sttPos]
  used[sttPos][char] = true
  q.add(Node(0, 0, 1, 1 shl char))

  var maxCnt = 1
  while (q.isNotEmpty()) {
    val node = q.poll()
    val r = node.r
    val c = node.c
    val cnt = node.cnt


    repeat(4) {
      val nr = dr[it] + r
      val nc = dc[it] + c
      if (!inRange(nr, nc)) return@repeat
      val pos = encodePos(nr, nc)
      val nextChar = chars[pos]
      val mask = node.nextMask(nextChar)
      val ch = used[pos]
      if (node.isUsed(nextChar) || ch[mask] == true) return@repeat
      ch[mask] = true
      val nCnt = cnt + 1

      q.add(Node(nr, nc, nCnt, mask))
      if (maxCnt < nCnt) maxCnt = nCnt
    }
  }

  w(maxCnt)
  O.flush()
}

private data class Node(
  var r: Int,
  var c: Int,
  var cnt: Int,
  var flag: Int,
) {

  fun nextMask(c: Int): Int = flag or (1 shl c)
  fun isUsed(c: Int): Boolean = flag and (1 shl c) != 0
}
