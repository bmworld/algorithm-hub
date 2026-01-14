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
private const val MAX_X_CNT = 5
private const val MAX_O_CNT = 4
private const val BOARD_SIZE = 9

private val p1 = intArrayOf(0, 3, 6, 0, 1, 2, 0, 2)
private val p2 = intArrayOf(1, 4, 7, 3, 4, 5, 4, 4)
private val p3 = intArrayOf(2, 5, 8, 6, 7, 8, 8, 6)

private val VALID = byteArrayOf(118, 97, 108, 105, 100, NL)
private val INVALID = byteArrayOf(105, 110, 118, 97, 108, 105, 100, NL)

fun main() {
  val a = ByteArray(BOARD_SIZE)
  var len = 0
  var xCnt = 0
  var oCnt = 0

  var b: Byte
  while (r().also { b = it } >= NL) {
    if (b == NL) continue
    when (b) {
      x -> xCnt++
      o -> oCnt++
      dot -> {}
      else -> break
    }
    a[len++] = b
    if (len != BOARD_SIZE) continue

    O.write(if (validate(a, xCnt, oCnt)) VALID else INVALID)

    len = 0
    xCnt = 0
    oCnt = 0
  }

  O.flush()
}

private fun validate(
  board: ByteArray,
  xCnt: Int,
  oCnt: Int
): Boolean {
  
  val dotCnt = BOARD_SIZE - (xCnt + oCnt)

  when {
    dotCnt == 0 -> {
      if (oCnt != MAX_O_CNT || xCnt != MAX_X_CNT) return false
      val (xLines, oLines) = calcLines(board)
      if (oLines > 0) return false
    }
    else -> {
      if (dotCnt > 4) return false
      val (xLines, oLines) = calcLines(board)
      if (xLines + oLines != 1
        || xLines == 1 && xCnt <= oCnt
        || oLines == 1 && xCnt != oCnt
      ) return false
    }
  }

  return true
}

private fun calcLines(board: ByteArray): Pair<Int, Int> {
  var xLines = 0
  var oLines = 0
  for (i in p1.indices) {
    val t1 = board[p1[i]]
    val t2 = board[p2[i]]
    val t3 = board[p3[i]]
    val line = t1 != dot && t1 == t2 && t2 == t3
    if (!line) continue
    if (t1 == x) xLines++
    else oLines++
  }
  return Pair(xLines, oLines)
}

/**
✅ OUT: valid
....OOXXX
XOXOXOXOX
XXOOOXXOX
XO.OX...X
X..XO.XO.
end

❌OUT: invalid
.........
......XXX
......XOO
....XOXOX
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
end
 */

//println("-- isFull=$full / lCnt=$lineCnt / $winner == $t1")
