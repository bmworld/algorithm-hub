import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 60_000
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

private val tetros = listOf(
  listOf(
    Pair(1, 0), Pair(0, 1), Pair(0, 1), Pair(0, 1), Pair(0, 1), Pair(1, 0), Pair(1, 0), Pair(1, 0), Pair(1, 0), Pair(0, 1), Pair(1, 0), Pair(0, 1), Pair(0, 1), Pair(0, 1), Pair(0, 1), Pair(0, 1), Pair(0, 1), Pair(1, 0), Pair(1, 0)
  ), listOf(
    Pair(2, 0), Pair(0, 2), Pair(1, 0), Pair(1, 0), Pair(1, 1), Pair(1, -1), Pair(1, 1), Pair(2, 0), Pair(2, 0), Pair(0, 2), Pair(1, 1), Pair(0, 2), Pair(0, 2), Pair(0, 2), Pair(0, 2), Pair(1, 1), Pair(-1, 1), Pair(1, 1), Pair(1, -1)
  ), listOf(
    Pair(3, 0), Pair(0, 3), Pair(1, 1), Pair(2, 0), Pair(2, 1), Pair(2, 0), Pair(2, 0), Pair(2, -1), Pair(2, 1), Pair(1, 0), Pair(1, 2), Pair(-1, 1), Pair(1, 1), Pair(-1, 2), Pair(1, 2), Pair(1, 2), Pair(-1, 2), Pair(2, 1), Pair(2, -1)
  )
)

fun main() {
  val rs = i()
  val cs = i()
  val a = Array(rs) { IntArray(cs) }
  repeat(rs) { r ->
    repeat(cs) { c ->
      a[r][c] = i()
    }
  }

  var max = 0
  repeat(rs) { r ->
    repeat(cs) { c ->
      val v = a[r][c]
      repeat(19) { i ->
        var sum = v
        for (j in 0..2) {
          val (tr, tc) = tetros[j][i]
          val nr = r + tr
          val nc = c + tc
          val inRange = nr in 0 until rs && nc in 0 until cs
          if (!inRange) break
          sum += a[nr][nc]
        }
        if (sum > max) max = sum
      }
    }
  }

  w(max)
  O.flush()
}