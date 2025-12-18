import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 6
private const val OBS = 1 shl 6
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
  while (true) {
    var c = r()
    if (c == (-1).toByte()) break
    var i = 0
    while (c >= 32) {
      WB[i++] = c
      c = r()
    }
    O.write(WB, 0, i)
    O.write('\n'.code)
  }
  O.flush()
}