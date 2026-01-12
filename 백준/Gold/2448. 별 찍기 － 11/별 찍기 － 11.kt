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

private val dr = intArrayOf(0, 1, 1, 2, 2, 2, 2, 2)
private val dc = intArrayOf(2, 1, 3, 0, 1, 2, 3, 4)

private const val MIN_WIDTH = 6

fun main() {
  val H = i()
  val W = H * 2
  val CAP = W + 1
  val SIZE = H * CAP
  val a = ByteArray(SIZE) {
    val EOF = it % CAP == CAP - 1
    if (EOF) NL else SPACE
  }

  fun twinkle(
    r: Int,
    c: Int,
    w: Int,
  ) {
    if (w <= MIN_WIDTH) {
      repeat(8) { i ->
        val nr = r + dr[i]
        val nc = c + dc[i]
        a[encodePos(nr, nc, CAP)] = STAR
      }
      return
    }

    repeat(3) { i ->
      val nr = r + when (i) {
        0 -> 0
        else -> w / 4
      }

      val nc = c + when (i) {
        0 -> w / 4
        1 -> 0
        else -> w / 2
      }

      twinkle(nr, nc, w / 2)
    }
  }

  twinkle(0, 0, W)

  val lastLineOffset = (H - 1) * CAP
  O.write(a, 0, lastLineOffset)
  O.write(a, lastLineOffset, SIZE - lastLineOffset - 2)

  O.flush()
}

private fun encodePos(
  r: Int,
  c: Int,
  CAP: Int,
): Int = r * CAP + c