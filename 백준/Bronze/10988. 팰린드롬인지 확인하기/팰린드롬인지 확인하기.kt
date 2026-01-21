import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 100
private const val OBS = 1 shl 1
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

private const val a = 97
private const val z = 122
private const val N = 48
private const val Y = 49
private const val MAX_LEN = 100

fun main() {

  val str = ByteArray(MAX_LEN)
  var len = 0
  var c: Byte
  while (r().also { c = it } in a..z || c == 10.toByte()) {
    if (c == 10.toByte()) break
    str[len++] = c
  }

  val half = (len + 1) / 2
  val last = len - 1
  var valid = true
  for (i in 0 until half) {
    if (str[i] == str[last - i]) continue
    valid = false
    break
  }

  O.write(if (valid) Y else N)
  O.flush()
}