import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 4
private const val OBS = 1 shl 2
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
  O.write(WB, ++pos, WS - pos)
}

private const val EQ = 15
private const val SQ = 28
private const val MQ = 19
fun main() {

  val e = i()
  val s = i()
  val m = i()

  var ei = 0
  var si = 0
  var mi = 0
  var i = 1
  while (true) {
    val ev = ei + 1
    val sv = si + 1
    val mv = mi + 1
    if (ev == e && sv == s && mv == m) break

    ei = (ei + 1) % EQ
    si = (si + 1) % SQ
    mi = (mi + 1) % MQ
    i++
  }

  w(i)
  O.flush()
}