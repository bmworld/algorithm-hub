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
private const val MAX_LEN = 9

private val p1 = intArrayOf(0, 3, 6, 0, 1, 2, 0, 2)
private val p2 = intArrayOf(1, 4, 7, 3, 4, 5, 4, 4)
private val p3 = intArrayOf(2, 5, 8, 6, 7, 8, 8, 6)

private val VALID = byteArrayOf(118, 97, 108, 97, 100, NL)
private val INVALID = byteArrayOf(105, 110, 118, 97, 108, 97, 100, NL)

fun main() {
  val a = ByteArray(MAX_LEN)
  var len = 0
  var oCnt = 0
  var xCnt = 0
  var dotCnt = 0

  var b: Byte
  while (r().also { b = it } >= 10) {
    if (b == NL) continue
    when (b) {
      o -> oCnt++
      x -> xCnt++
      dot -> dotCnt++
      else -> break
    }
    a[len++] = b
    if (len != MAX_LEN) continue

    when (dotCnt) {
      0 -> O.write(if ((oCnt != O_MAX_CNT || xCnt != X_MAX_CNT)) INVALID else VALID)
      else -> O.write(if (validTicTacToe(a, xCnt, oCnt)) VALID else INVALID)
    }

    len = 0
    oCnt = 0
    xCnt = 0
    dotCnt = 0
  }

  O.flush()
}

private fun validTicTacToe(
  board: ByteArray,
  xCnt: Int,
  oCnt: Int
): Boolean {
  var valid = false
  for (i in p1.indices) {
    val v1 = board[p1[i]]
    val v2 = board[p2[i]]
    val v3 = board[p3[i]]
    if (v1 != dot
      && v1 == v2 && v2 == v3
      && (v1 == x && xCnt > oCnt || v1 == o && xCnt == oCnt)
    ) {
      if (!valid) valid = true
      else {
        valid = false
        break
      }
    }
  }
  return valid
}
