import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import kotlin.math.sqrt

private const val IBS = 1 shl 10
private const val OBS = 1 shl 8
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

    val dist = getDist(x1, x2, y1, y2)
    val sum = (rad1 + rad2).toDouble()

    w(
      when {
        dist > sum -> 0
        dist == sum -> 1
        else -> {
          when {
            dist == 0.0 -> if (rad1 == rad2) -1 else 0
            else -> {
              val smallRad = minOf(rad1, rad2).toDouble()
              val largeRad = maxOf(rad1, rad2).toDouble()
              when {
                dist + smallRad > largeRad -> 2
                dist + smallRad == largeRad -> 1
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

private fun getDist(
  x1: Int,
  x2: Int,
  y1: Int,
  y2: Int,
): Double {
  val sx = abs(x1 - x2).toDouble()
  val sy = abs(y1 - y2).toDouble()
  return sqrt(sx * sx + sy * sy)
}

private fun abs(v: Int): Int = (if (v > 0) v else -v)