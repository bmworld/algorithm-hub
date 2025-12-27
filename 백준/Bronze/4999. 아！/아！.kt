import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1_000
private const val OBS = 16
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

private fun getCnt(): Int {
  var cnt = 0
  var b: Byte = 0
  while (r().also { b = it } >= 10) {
    when (b.toInt()) {
      97 -> cnt++
      104 -> break
      else -> continue
    }
  }
  return cnt
}


private val GO = byteArrayOf(103, 111)
private val NO = byteArrayOf(110, 111)
fun main() {
  O.write(if (getCnt() >= getCnt()) GO else NO)
  O.flush()
}