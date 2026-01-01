import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 17
private const val OBS = 1 shl 10
private val O = BufferedOutputStream(System.`out`, OBS)
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

private const val ZERO = 48
private fun b(): Int {
  var b: Byte
  while (r().also { b = it } >= 10) if (b == ZERO.toByte() || b == (ZERO + 1).toByte()) break
  return b - ZERO
}

private const val WS = 30
private val WB = ByteArray(WS)
private fun w(
  num: Long,
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

private const val EMPTY = 0
fun main() {

  val N = i()
  val a = Array(N) { IntArray(N) { b() } }

  if (a[0][2] == 1 || a[N - 1][N - 1] == 1) {
    w(0)
    O.flush()
    return
  }

  val cnts = LongArray(
    N * N * 3 // [H, V, D]
  )
  cnts[encodePos(0, 1, N)] = 1

  repeat(N) { r ->
    repeat(N - 2) {
      val c = it + 2
      if (a[r][c] != EMPTY) return@repeat

      val h = encodePos(r, c, N)
      val v = h + 1
      val d = h + 2

      val pr = r - 1
      val pc = c - 1

      val movableH = inRange(r, pc, N) && a[r][pc] == EMPTY
      if (movableH) {
        val ph = encodePos(r, pc, N)
        cnts[h] = cnts[ph] + cnts[ph + 2]
      }

      val movableV = inRange(pr, c, N) && a[pr][c] == EMPTY
      if (movableV) {
        val ph = encodePos(pr, c, N)
        cnts[v] = cnts[ph + 1] + cnts[ph + 2]
      }

      if (movableH && movableV) {
        val ph = encodePos(pr, pc, N)
        cnts[d] = cnts[ph] + cnts[ph + 1] + cnts[ph + 2]
      }
    }
  }

  val t = N - 1
  val h = encodePos(t, t, N)
  w(cnts[h] + cnts[h + 1] + cnts[h + 2])
  O.flush()
}

private fun inRange(
  r: Int,
  c: Int,
  size: Int,
) = r in 0 until size && c in 0 until size

private fun encodePos(
  r: Int,
  c: Int,
  size: Int,
) = 3 * (r * size + c)