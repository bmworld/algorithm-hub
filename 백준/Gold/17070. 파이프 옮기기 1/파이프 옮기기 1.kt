import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 50_000
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

private const val EMPTY = 0
private const val H_SEP = 1_000_000_000_000UL
private const val V_SEP = 1_000_000UL

@OptIn(ExperimentalUnsignedTypes::class)
fun main() {

  val N = i()
  val a = Array(N) { IntArray(N) { b() } }

  if (a[0][2] == 1 || a[N - 1][N - 1] == 1) {
    w(0)
    O.flush()
    return
  }

  val cnts = Array(N) { ULongArray(N) }
  cnts[0][1] = encodeCnt(h = 1UL)

  repeat(N) { r ->
    repeat(N - 2) {
      val c = it + 2
      if (a[r][c] != EMPTY) return@repeat

      var nh = 0UL
      val movableH = inRange(r, c - 1, N) && a[r][c - 1] == EMPTY
      if (movableH) {
        val hvd = cnts[r][c - 1]
        val vd = hvd % H_SEP
        val h = hvd / H_SEP
        val d = vd % V_SEP
        nh = h + d
      }

      var nv = 0UL
      val movableV = inRange(r - 1, c, N) && a[r - 1][c] == EMPTY
      if (movableV) {
        val hvd = cnts[r - 1][c]
        val vd = hvd % H_SEP
        val v = vd / V_SEP
        val d = vd % V_SEP
        nv = v + d
      }

      var nd = 0UL
      if (movableH && movableV) {
        val hvd = cnts[r - 1][c - 1]
        val vd = hvd % H_SEP
        val h = hvd / H_SEP
        val v = vd / V_SEP
        val d = vd % V_SEP
        nd = h + v + d
      }

      cnts[r][c] = encodeCnt(nh, nv, nd)
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
  h: ULong = 0UL,
  v: ULong = 0UL,
  d: ULong = 0UL,
): ULong = h * H_SEP + v * V_SEP + d