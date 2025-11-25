import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 15
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

private const val A = 65
private const val a = 97
private const val LEN = 26
private val UC = A until A + LEN
private val LC = a until a + LEN

private fun s(): Int {
  return when (val c = r()) {
    in UC -> c - A
    in LC -> c - a
    else -> -1
  }
}

fun main() {
  val ch = IntArray(LEN)
  var i: Int
  while (s().also { i = it } != -1) ch[i]++

  var fi = 0
  var si = 0
  var maxCnt = 0
  for (i in 0 until LEN) {
    val cnt = ch[i]
    if (cnt > maxCnt) {
      maxCnt = cnt
      fi = i
      si = i
    } else if (cnt == maxCnt) {
      si = i
    }
  }

  O.write(
    when {
      fi != si -> 63
      else -> fi + A
    }
  )
  O.flush()
}