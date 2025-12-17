import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 50_000
private const val OBS = 1_000
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
  pos++
  O.write(WB, pos, WS - pos)
}

private val pos2 = listOf(
  Pair(1, 0), Pair(0, 1), Pair(0, 1), Pair(0, 1), Pair(0, 1), Pair(1, 0), Pair(1, 0), Pair(1, 0), Pair(1, 0), Pair(0, 1), Pair(1, 0), Pair(0, 1), Pair(0, 1), Pair(0, 1), Pair(0, 1), Pair(0, 1), Pair(0, 1), Pair(1, 0), Pair(1, 0)
)
private val pos3 = listOf(
  Pair(2, 0), Pair(0, 2), Pair(1, 0), Pair(1, 0), Pair(1, 1), Pair(1, -1), Pair(1, 1), Pair(2, 0), Pair(2, 0), Pair(0, 2), Pair(1, 1), Pair(0, 2), Pair(0, 2), Pair(0, 2), Pair(0, 2), Pair(1, 1), Pair(-1, 1), Pair(1, 1), Pair(1, -1)
)
private val pos4 = listOf(
  Pair(3, 0), Pair(0, 3), Pair(1, 1), Pair(2, 0), Pair(2, 1), Pair(2, 0), Pair(2, 0), Pair(2, -1), Pair(2, 1), Pair(1, 0), Pair(1, 2), Pair(-1, 1), Pair(1, 1), Pair(-1, 2), Pair(1, 2), Pair(1, 2), Pair(-1, 2), Pair(2, 1), Pair(2, -1)
)

private val posArr = listOf(pos2, pos3, pos4)

private const val OUT_OF_RANGE = -1

fun main() {
  val rs = i()
  val cs = i()
  val a = Array(rs) { IntArray(cs) }
  repeat(rs) { r ->
    repeat(cs) { c ->
      a[r][c] = i()
    }
  }

  fun getV(
    r: Int,
    c: Int,
    pos: Pair<Int, Int>,
  ): Int {
    val nr = r + pos.first
    val nc = c + pos.second
    val inRange = nr in 0 until rs && nc in 0 until cs
    return if (inRange) a[nr][nc] else OUT_OF_RANGE
  }

  var max = 0
  repeat(rs) { r ->
    repeat(cs) { c ->
      val v1 = a[r][c]
      repeat(pos2.size) { i ->
        var sum = v1
        pos@ for (j in 0..2) {
          val pos = posArr[j]
          val v = getV(r, c, pos[i])
          if (v == OUT_OF_RANGE) break@pos
          sum += v
        }
        if (sum > max) max = sum
      }
    }
  }

  w(max)
  O.flush()
}