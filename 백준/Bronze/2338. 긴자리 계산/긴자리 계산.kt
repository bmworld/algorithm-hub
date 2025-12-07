import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.math.BigInteger

private const val IBS = 2_024
private const val OBS = 2_000
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

private val NUM = 48..57
private val inBuf = StringBuilder(1000)
private fun i(): BigInteger {
  var c: Byte
  while (r().also { c = it } in NUM || c == 45.toByte()) {
    when (c) {
      in NUM -> inBuf.append(c - 48)
      else -> inBuf.append('-')
    }
  }
  val bint = inBuf.toString()
    .toBigInteger()
  inBuf.setLength(0)
  return bint
}

fun main() {
  val a = i()
  val b = i()
  println(a + b)
  println(a - b)
  println(a * b)
  O.flush()
}