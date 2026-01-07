import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 16
private const val OBS = 1 shl 4
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

private const val END: Byte = 10
fun main() {
  var cnt = 0
  var c: Byte
  var prev: Byte? = null
  while (r().also { c = it } >= END) {
    if (c == END) break
    if (prev == null || c != prev) cnt++
    prev = c
  }

  w(cnt / 2)
  O.flush()
}