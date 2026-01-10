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

private const val ALPHABET_SIZE = 26
private val dr = intArrayOf(1, 0, -1, 0)
private val dc = intArrayOf(0, 1, 0, -1)
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
  val q = PriorityQueue(compareBy<Node> { it.r }.thenBy { it.c }
    .thenBy { it.cnt })
  val stt = Node(0, 0, 1)
  val sss = chars[encodePos(0, 0)]
  stt.useChar(sss)
  q.add(stt)

  var maxCnt = 0
  while (q.isNotEmpty()) {
    val node = q.poll()
    val r = node.r
    val c = node.c
    val cnt = node.cnt
    val used = node.ch
    if (maxCnt < cnt) maxCnt = cnt

    repeat(4) {
      val nr = dr[it] + r
      val nc = dc[it] + c
      if (!inRange(nr, nc)) return@repeat
      val nPos = encodePos(nr, nc)
      val char = chars[nPos]
      val nextCnt = cnt + 1
      if (used[char]) return@repeat
      val next = Node(nr, nc, nextCnt, used.copyOf())
      next.useChar(char)
      q.add(next)
    }
  }

  w(maxCnt)
  O.flush()
}

private data class Node(
  var r: Int,
  var c: Int,
  var cnt: Int,
  val ch: BooleanArray = BooleanArray(ALPHABET_SIZE),
) {

  fun useChar(char: Int) {
    ch[char] = true
  }
}

//println("---- $r, $c ($cnt)")
//repeat(ALPHABET_SIZE) { char ->
//  if (used[char]) println("used[${(char + A).toChar()}]")
//}
