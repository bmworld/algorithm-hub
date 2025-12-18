import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 10_000
private const val OBS = 1
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

private const val WS = 10
private val WB = ByteArray(WS)

private fun w(
  num: Int,
) {
  var v = num
  var end = WS - 1
  do {
    WB[end--] = ((v % 10) + 48).toByte()
    v /= 10
  } while (v > 0)
  val stt = end + 1
  O.write(WB, stt, -stt + WS)
}

private const val MAX_LEN = 200_000
private val inBuf = ByteArray(MAX_LEN)
private fun s(): ByteArray {
  var c = r()
  while (c != EOF.toByte() && c <= 32) c = r()
  var len = 0
  while (c >= 48) {
    if (c in 65..90 || c in 97..122) inBuf[len++] = c
    c = r()
  }
  return inBuf.copyOf(len)
}

fun main() {
  val a = s()
  val k = s()
  val kLen = k.size
  var j = 0
  for (i in 0 until a.size) {
    when {
      a[i] == k[j] -> if (++j == kLen) break
      else -> while (j > 0) if (a[i] == k[--j]) {
        j++
        break
      }
    }
  }

  w(if (j == kLen) 1 else 0)
  O.flush()
}