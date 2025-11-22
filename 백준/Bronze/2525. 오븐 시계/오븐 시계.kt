import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 3
private const val OBS = 1 shl 3
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
  var n = 0
  var c: Byte
  while (r().also { c = it } in 48..57) n = n * 10 + (c - 48)
  return n
}

private const val WS = 3
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
  val h = i()
  val m = i()
  val t = i()
  w((h + (m + t) / 60) % 24)
  w((m + t) % 60)
  O.flush()
}