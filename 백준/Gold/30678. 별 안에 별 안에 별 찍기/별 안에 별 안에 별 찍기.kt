import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 8
private const val OBS = 1 shl 18
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

private const val NL: Byte = 10
private const val STAR: Byte = 42
private const val SPACE: Byte = 32
private const val MIN_SIZE = 5
private const val MAX_N = 5
private const val DELTA = MIN_SIZE
private val dr = intArrayOf(0, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4)
private val dc = intArrayOf(2, 2, 0, 1, 2, 3, 4, 1, 2, 3, 1, 3)
fun main() {
  val N = i()
  if (N <= 0) {
    O.write(42)
    O.flush()
    return
  }
  val W = deltas[N]
  val CAP = W + 1
  val a = ByteArray(W * CAP) {
    if (it % CAP == CAP - 1) NL else SPACE
  }

  fun twinkle(
    r: Int,
    c: Int,
    dep: Int,
  ) { //    println("[$dep] w=$w -> $r, $c")
    if (dep <= 1) {
      repeat(12) { i ->
        val nr = r + dr[i]
        val nc = c + dc[i]
        a[encodePos(nr, nc, CAP)] = STAR
      }
      return
    }

    val delta = deltas[dep - 1]
    repeat(12) { i ->
      val nr = r + dr[i] * delta
      val nc = c + dc[i] * delta
      twinkle(nr, nc, dep - 1)
    }
  }

  twinkle(0, 0, N)

  O.write(a)
  O.flush()
}

private val deltas = IntArray(MAX_N + 1).also {
  repeat(MAX_N) { i ->
    val N = i + 1
    it[N] = pow(DELTA, N)
  }
}

private fun pow(
  base: Int,
  exp: Int,
): Int {
  var W = 1
  var i = exp
  while (i-- > 0) W *= base
  return W
}

private fun encodePos(
  r: Int,
  c: Int,
  CAP: Int,
): Int = r * CAP + c