import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 10_000
private const val OBS = 1_000
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

private const val H = 0
private const val V = 1
private const val D = 2
private const val EMPTY = 0
private const val H_SEP = 1_000_000_000_000UL
private const val V_SEP = 1_000_000UL
private val dr = intArrayOf(0, 1, 1)
private val dc = intArrayOf(1, 0, 1)

@OptIn(ExperimentalUnsignedTypes::class)
fun main() {

  val N = i()
  val a = Array(N) { IntArray(N) { b() } }

  if (a[N - 1][N - 1] == 1) {
    w(0)
    O.flush()
    return
  }

  val cnts = Array(N) { ULongArray(N) }
  cnts[0][1] = encodeCnt(1UL, 0UL, 0UL)

  fun move(
    r: Int,
    c: Int,
    h: ULong = 0UL,
    v: ULong = 0UL,
    d: ULong = 0UL,
  ) {
    val hvd = cnts[r][c]
    val vd = hvd % H_SEP
    val nh = hvd / H_SEP + h
    val nv = vd / V_SEP + v
    val nd = vd % V_SEP + d
    cnts[r][c] = encodeCnt(nh, nv, nd)
  }

  repeat(N) { r ->
    repeat(N) { c ->
      val hvd = cnts[r][c]
      val vd = hvd % H_SEP
      val h = hvd / H_SEP
      val v = vd / V_SEP
      val d = vd % V_SEP

      val hasH = h > 0UL
      val hasV = v > 0UL
      val hasD = d > 0UL

      for (dir in 0..2) {
        val nr = r + dr[dir]
        val nc = c + dc[dir]
        val movable = inRange(nr, nc, N) && a[nr][nc] == EMPTY && if (dir == D) a[nr][c] == EMPTY && a[r][nc] == EMPTY else true
        if (!movable) continue

        if (dir == H && (hasH || hasD)) move(nr, nc, h = h + d)
        if (dir == V && (hasV || hasD)) move(nr, nc, v = v + d)
        if (dir == D && (hasH || hasV || hasD)) move(nr, nc, d = h + v + d)
      }
    }
  }

  val hvd = cnts[N - 1][N - 1]
  val vd = hvd % H_SEP
  val h = hvd / H_SEP
  val v = vd / V_SEP
  val d = vd % V_SEP

  w((h + v + d).toInt())
  O.flush()
}

private fun inRange(
  r: Int,
  c: Int,
  size: Int,
) = c in 0 until size && r in 0 until size

private fun encodeCnt(
  h: ULong,
  v: ULong,
  d: ULong,
): ULong = h * H_SEP + v * V_SEP + d