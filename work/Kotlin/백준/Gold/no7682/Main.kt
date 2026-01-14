package 백준.Gold.no7682

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 16
private const val OBS = 1 shl 10
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

private const val NL: Byte = 10
private const val x: Byte = 88
private const val o: Byte = 79
private const val dot: Byte = 46
private const val X_MAX_CNT = 5
private const val O_MAX_CNT = 4
private const val MAX_CNT = 9

private val p1 = intArrayOf(0, 3, 6, 0, 1, 2, 0, 2)
private val p2 = intArrayOf(1, 4, 7, 3, 4, 5, 4, 4)
private val p3 = intArrayOf(2, 5, 8, 6, 7, 8, 8, 6)

private val VALID = byteArrayOf(118, 97, 108, 105, 100, NL)
private val INVALID = byteArrayOf(105, 110, 118, 97, 108, 105, 100, NL)

fun main() {
  val a = ByteArray(MAX_CNT)
  var len = 0
  var xCnt = 0
  var oCnt = 0
  var dotCnt = 0

  var b: Byte
  while (r().also { b = it } >= 10) {
    if (b == NL) continue
    when (b) {
      x -> xCnt++
      o -> oCnt++
      dot -> dotCnt++
      else -> break
    }
    a[len++] = b
    if (len != MAX_CNT) continue

    O.write(if (validate(a, xCnt, oCnt, dotCnt)) VALID else INVALID)

    len = 0
    xCnt = 0
    oCnt = 0
    dotCnt = 0
  }

  O.flush()
}

private fun validate(
  board: ByteArray,
  xCnt: Int,
  oCnt: Int,
  dotCnt: Int
): Boolean {

  val full = dotCnt == 0
  if (full && (oCnt != O_MAX_CNT || xCnt != X_MAX_CNT)
    || !full && dotCnt >= 5
  ) return false

  var valid = true
  var lineCnt = 0
  var winner: Byte = -1
  for (i in p1.indices) {
    val t1 = board[p1[i]]
    val t2 = board[p2[i]]
    val t3 = board[p3[i]]
    val line = t1 != dot && t1 == t2 && t2 == t3
    if (!line) continue

    if (
      full && (t1 == o || lineCnt > 0 && winner != t1)
      || !full && lineCnt > 0
      || t1 == o && xCnt != oCnt
      || t1 == x && xCnt <= oCnt
    ) {
      valid = false
      break
    }

    winner = t1
    lineCnt++
  }

  return valid
}

/**
✅ OUT: valid
XOXOXOXOX
XXOOOXXOX
XO.OX...X
X..XO.XO.

❌OUT: invalid
.........
OOOOOOOOO
XXXXXXXXX
X.XX.XX.X
XXXOO.XXX
OXOXOXOXO
.XXX.XOOO
X.OO..X..
OOXXXOOXO
OXXXOXXOO
OOOXX....

 */

//println("-- isFull=$full / lCnt=$lineCnt / $winner == $t1")
