package 백준.Silver.no10994

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

fun main() {
  val n = i()
  val SIZE = (n - 1) * 4 + 1
  val HALF = SIZE / 2
  val CAP = SIZE + 1
  val stars = ByteArray(SIZE * CAP) { i ->
    val EOL = i % CAP == CAP - 1
    if (EOL) NL else STAR
  }

  fun punchHole(layer: Int) {
    if (layer <= 1) return
    punchHole(layer - 2)
    if (layer % 2 == 1) return

    val stt = HALF - layer + 1
    val end = SIZE - (stt + 1)
    repeat(end - stt + 1) {
      val r = stt + it
      stars[encodePos(r, stt, CAP)] = SPACE
      stars[encodePos(r, end, CAP)] = SPACE
    }
    repeat(end - stt - 1) {
      val c = stt + it + 1
      stars[encodePos(stt, c, CAP)] = SPACE
      stars[encodePos(end, c, CAP)] = SPACE
    }
  }

  punchHole(HALF)
  O.write(stars)
  O.flush()
}

fun encodePos(
  r: Int,
  c: Int,
  CAP: Int,
): Int = r * CAP + c
