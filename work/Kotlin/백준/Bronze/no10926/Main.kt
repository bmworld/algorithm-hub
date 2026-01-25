package 백준.Bronze.no10926

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 6
const val OBS = 1 shl 6
val O = BufferedOutputStream(System.`out`, OBS)
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

val suffix = byteArrayOf(63, 63, 33)
fun main() {

  var str = ByteArray(50)
  var len = 0
  var b: Byte
  while (r().also { b = it.toByte() } >= 10) {
    if (b == 10.toByte()) break
    str[len++] = b
  }
  O.write(str, 0, len)
  O.write(suffix)
  O.flush()
}
