import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 3
private const val OBS = 1 shl 2
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



private const val WS = 3
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

private fun i(): Int {
  var n = 0
  val i1 = r() - 48
  val i2 = r() - 48
  val i3 = r() - 48
  n += i3 * 100
  n += i2 * 10
  n += i1
  r()
  return n
}

fun main() {
  val a = i()
  val b = i()
  w(if (a > b) a else b)
  O.flush()
}