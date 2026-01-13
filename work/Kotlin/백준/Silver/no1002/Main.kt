package 백준.Silver.no1002

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 12
private const val OBS = 1 shl 5
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

private const val WS = 10
private val WB = ByteArray(WS + 1).also { it[WS] = 10 }
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
  O.write(WB, ++pos, WS - pos + 1)
}

fun main() {

  repeat(i()) {
    val x1 = i()
    val y1 = i()
    val rad1 = i()

    val x2 = i()
    val y2 = i()
    val rad2 = i()

    val sqrdDist = getSquaredDist(x1, x2, y1, y2)
    val sumOfRad = rad1 + rad2

    w(
      when {
        sqrdDist > sumOfRad * sumOfRad -> 0
        sqrdDist == sumOfRad * sumOfRad -> 1
        else -> {
          when {
            sqrdDist == 0 -> if (rad1 == rad2) -1 else 0
            else -> {
              val radDiff = abs(rad2 - rad1)
              when {
                sqrdDist > radDiff * radDiff -> 2
                sqrdDist == radDiff * radDiff -> 1
                else -> 0
              }
            }
          }
        }
      }
    )
  }
  O.flush()
}

private fun getSquaredDist(
  x1: Int,
  x2: Int,
  y1: Int,
  y2: Int,
): Int {
  val sx = abs(x1 - x2)
  val sy = abs(y1 - y2)
  return sx * sx + sy * sy
}

private fun abs(v: Int): Int = if (v > 0) v else -v
