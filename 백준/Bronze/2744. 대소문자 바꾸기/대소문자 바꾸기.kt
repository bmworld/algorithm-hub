import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 7
private const val OBS = 1 shl 4
private const val EOF = -1
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
private val IB = ByteArray(IBS)
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

private const val WS = 100
private val WB = ByteArray(WS)

fun main() {
  var i = 0
  var c: Byte
  while (r().also { c = it } >= 65) {
    c = when (c) {
      in 65..90 -> (c + 32).toByte()
      in 97..122 -> (c - 32).toByte()
      else -> break
    }
    WB[i++] = c
  }
  O.write(WB, 0, i)
  O.flush()
}