import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.math.BigInteger

private const val IBS = 10_000
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

private val bigInt0 = BigInteger.ZERO
private val bigInt10 = BigInteger.valueOf(10)
private val bigInt48 = BigInteger.valueOf(48)
private const val WS = 1_000_001
private val WB = ByteArray(WS + 1).also { it[WS] = '\n'.code.toByte() }
private fun w(
  num: BigInteger,
) {
  var v = num
  var end = WS - 1
  if (v < bigInt0) {
    O.write('-'.code)
    v = -v
  }
  do {
    WB[end--] = (v.rem(bigInt10)
      .plus(bigInt48)).toByte()
    v = v.divide(bigInt10)
  } while (v > bigInt0)
  val stt = end + 1
  O.write(WB, stt, -stt + WS + 1)
}

fun main() {
  val a = i()
  val b = i()
  w(a + b)
  w(a - b)
  w(a * b)
  O.flush()
}