import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 14
private const val OBS = 1 shl 6
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
private val IB = ByteArray(IBS)
private const val EOF = -1
private var Ii = 0
private var Il = 0

private fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

private fun i(): Int {
  var v = 0
  var c: Byte
  while (r().also { c = it } in 48..57) v = v * 10 + (c - 48)
  return v
}

private const val WS = 10
private val WB = ByteArray(WS + 1).also { it[WS] = ' '.code.toByte() }

private fun w(
  num: Int,
) {
  var x = num
  var end = WS - 1
  do {
    WB[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = end + 1
  O.write(WB, stt, WS - stt + 1)
}

fun main() {
  val n = i()
  val m = i()
  val initB = i()
  val estLen = n * m
  val est = IntArray(estLen)
  var minH = 256
  var maxH = 0
  var i = 0
  repeat(estLen) {
    val h = i()
    est[i++] = h
    if (h < minH) minH = h
    if (h > maxH) maxH = h
  }

  fun even(h: Int): Int {
    var t = 0
    var b = initB
    for (i in 0 until estLen) {
      val curH = est[i]
      val diff = curH - h
      if (diff == 0) continue
      if (diff > 0) {
        b += diff
        t += diff * 2
      } else {
        b -= -diff
        t += -diff
      }
    }
    return if (b < 0) -1 else t
  }

  val avgH = (minH + maxH) / 2
  var finalH = -1
  var finalT = Int.MAX_VALUE

  // 선
  for (h in avgH..maxH) {
    val t = even(h)
    if (t < 0) break
    if (t <= finalT) {
      finalH = h
      finalT = t
    }
  }

  // 후
  for (h in avgH - 1 downTo minH) {
    val t = even(h)
    if (t < 0) continue
    if (t < finalT) {
      finalH = h
      finalT = t
    }
  }

  w(finalT)
  w(finalH)
  O.flush()
}