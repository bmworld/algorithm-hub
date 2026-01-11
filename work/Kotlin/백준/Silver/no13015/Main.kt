package 백준.Silver.no13015

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

private const val NL = 10
private const val STAR: Byte = 42
private const val SPACE: Byte = 32

fun main() {
  val n = i()
  val rSize = 2 * n - 1
  val cSize = 2 * n - 1 + (n - 1) * 2
  val cHalf = cSize / 2
  val line = ByteArray(cSize) { SPACE }

  repeat(rSize) { r ->
    val stars = line.copyOf()
    val dist = abs(r + 1 - n)
    val isSide = r == 0 || r == rSize - 1
    var len = -1
    repeat(2) { i ->
      val rightSide = i == 0
      val stt = cHalf + dist * if (rightSide) 1 else -1
      stars[stt] = STAR

      val end = stt + (n - 1) * if (rightSide) 1 else -1
      stars[end] = STAR
      if (rightSide) len = end + 1

      if (isSide) {
        repeat(n - 2) { j ->
          val w = (1 + j) * if (rightSide) 1 else -1
          stars[stt + w] = STAR
        }
      }
    }
    O.write(stars, 0, len)
    O.write(NL)
  }

  O.flush()
}

private fun abs(
  v: Int,
) = if (v < 0) -v else v
