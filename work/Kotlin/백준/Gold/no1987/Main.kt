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
private const val ALPHABET_MAX = 26
private const val R_SEP = 10_000_000_000L
private const val C_SEP = R_SEP * 100
private const val CNT_SEP = C_SEP * 100

fun main() {
  val rSize = i()
  val cSize = i()

  val chars = IntArray(rSize * cSize) { b() }
  val flags = IntArray(rSize * cSize)
  var maxCnt = 1

  val q = PriorityQueue<Long>()
  val sttPos = encodePos(0, 0, cSize)
  val sttFlag = getFlag(0, chars[sttPos])
  flags[sttPos] = sttFlag
  q.add(qPos(0, 0, 1, sttFlag))

  bfs@ while (q.isNotEmpty()) {
    val e = q.poll()
    val cnt = (e / CNT_SEP).toInt()
    val crf = e % CNT_SEP
    val c = (crf / C_SEP).toInt()
    val rf = crf % C_SEP
    val r = (rf / R_SEP).toInt()
    val flag = (rf % R_SEP).toInt()

    for (i in 0..3) {
      val nr = dr[i] + r
      val nc = dc[i] + c
      if (!inRange(nr, nc, rSize, cSize)) continue
      val nextPos = encodePos(nr, nc, cSize)
      val char = chars[nextPos]
      val nextFlag = getFlag(flag, char)
      if (isUsed(flag, char) || flags[nextPos] == nextFlag) continue
      flags[nextPos] = nextFlag
      val nCnt = cnt + 1
      if (maxCnt < nCnt) maxCnt = nCnt
      if (maxCnt >= ALPHABET_MAX) break@bfs
      q.add(qPos(nr, nc, nCnt, nextFlag))
    }
  }

  w(maxCnt)
  O.flush()
}

private fun qPos(
  r: Int,
  c: Int,
  cnt: Int,
  flag: Int,
): Long = cnt * CNT_SEP + c * C_SEP + r * R_SEP + flag

private fun isUsed(
  flag: Int,
  char: Int,
): Boolean = flag and (1 shl char) != 0

private fun encodePos(
  r: Int,
  c: Int,
  CAP: Int,
): Int = r * CAP + c

private fun getFlag(
  flag: Int,
  char: Int,
): Int = flag or (1 shl char)

private fun inRange(
  r: Int,
  c: Int,
  rSize: Int,
  cSize: Int,
) = r in 0 until rSize && c in 0 until cSize

//    println("$cnt (${flag.toString(2)}) --- $r, $c (${(chars[encodePos(r, c, cSize)] + A).toChar()})")
