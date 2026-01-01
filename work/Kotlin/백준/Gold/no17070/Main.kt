package 백준.Gold.no17070

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.util.*

private const val IBS = 256
private const val OBS = 64
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
private const val POS_SEP = 100

private const val H_SEP = 1_000_000_000_000UL
private const val V_SEP = 1_000_000UL

@OptIn(ExperimentalUnsignedTypes::class)
fun main() {

  val N = i()
  val a = Array(N) { IntArray(N) { b() } }

  if (a[0][0] == 1 || a[0][1] == 1 || a[0][2] == 1) {
    w(0)
    O.flush()
    return
  }

  val cnts = Array(N) { ULongArray(N) }
  val q = PriorityQueue<Int>()
  q.add(0 * POS_SEP + 1)
  cnts[0][1] = encodeCnt(1UL, 0UL, 0UL)


  fun move(
    r: Int,
    c: Int,
    h: ULong,
    d: ULong,
    v: ULong,
    dir: Int,
  ) {
    val hvd = cnts[r][c]
    val vd = hvd % H_SEP
    val nh = hvd / H_SEP + if (dir == H) (h + d) else 0UL
    val nv = vd / V_SEP + if (dir == V) (v + d) else 0UL
    val nd = vd % V_SEP + if (dir == D) (h + v + d) else 0UL
    cnts[r][c] = encodeCnt(nh, nv, nd)

    val notUsed = hvd == 0UL
    if (notUsed) q.add(r * POS_SEP + c)
  }

  while (q.isNotEmpty()) {
    val rc = q.poll()
    val r = rc / POS_SEP
    val c = rc % POS_SEP

    val hvd = cnts[r][c]
    val vd = hvd % H_SEP
    val h = hvd / H_SEP
    val v = vd / V_SEP
    val d = vd % V_SEP

    val nc = c + 1
    val nr = r + 1

    val movableH = nc in 0 until N && a[r][nc] == EMPTY
    val movableV = nr in 0 until N && a[nr][c] == EMPTY
    val movableD = movableH && movableV && a[nr][nc] == EMPTY

    if (movableH && (h > 0UL || d > 0UL)) move(r, nc, h, d, v, H)
    if (movableV && (v > 0UL || d > 0UL)) move(nr, c, h, d, v, V)
    if (movableD && (h > 0UL || v > 0UL || d > 0UL)) move(nr, nc, h, d, v, D)

    println(
      "--- $r, $c ($h, $v, $d) -> $nr, $nc"
    )

  }

  val hvd = cnts[N - 1][N - 1]
  val vd = hvd % H_SEP
  val h = hvd / H_SEP
  val v = vd / V_SEP
  val d = vd % V_SEP

  w((h + v + d).toInt())
  O.flush()
}

private fun encodeCnt(
  h: ULong,
  v: ULong,
  d: ULong,
): ULong = h * H_SEP + v * V_SEP + d
