import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 30_000
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

  val cntsV = Array(N) { IntArray(N) }
  val cntsH = Array(N) { IntArray(N) }
  val cntsD = Array(N) { IntArray(N) }
  cntsH[0][1] = 1

  repeat(N) { r ->
    repeat(N - 2) {
      val c = it + 2
      if (a[r][c] != EMPTY) return@repeat

      val movableH = inRange(r, c - 1, N) && a[r][c - 1] == EMPTY
      if (movableH) cntsH[r][c] = cntsH[r][c - 1] + cntsD[r][c - 1]

      val movableV = inRange(r - 1, c, N) && a[r - 1][c] == EMPTY
      if (movableV) cntsV[r][c] = cntsV[r - 1][c] + cntsD[r - 1][c]

      if (movableH && movableV) cntsD[r][c] = cntsH[r - 1][c - 1] + cntsV[r - 1][c - 1] + cntsD[r - 1][c - 1]
    }
  }

  val t = N - 1
  w(cntsH[t][t] + cntsV[t][t] + cntsD[t][t])
  O.flush()
}

private fun inRange(
  r: Int,
  c: Int,
  size: Int,
) = c in 0 until size && r in 0 until size