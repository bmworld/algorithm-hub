import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 1
private const val OBS = 1 shl 1
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

private const val WS = 3
private const val PLUS = 43.toByte()
private const val MINUS = 45.toByte()
private val WB = ByteArray(WS).also { it[1] = '.'.code.toByte() }

fun main() {
  var l = 117 - r()
  WB[2] = when (r()) {
    PLUS -> 51

    MINUS -> {
      l--
      55
    }

    else -> 48
  }


  WB[0] = (if (l < 48) 48 else l).toByte()
  O.write(WB)
  O.flush()
}