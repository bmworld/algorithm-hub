import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 12
private const val OBS = 1 shl 4
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



private const val WS = 7
private val WB = ByteArray(WS)

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

  O.write(
    WB, stt, WS - stt
  )
}

private const val SPACE = 32.toByte()

fun main() {
  var cnt = 0
  var inWord = false
  var c: Byte
  while (r().also { c = it } >= SPACE) {
    if (c != SPACE) {
      if (!inWord) cnt++
      inWord = true
    } else inWord = false
  }
  w(cnt)
  O.flush()
}