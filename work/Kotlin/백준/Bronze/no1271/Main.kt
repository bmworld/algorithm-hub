package 백준.Bronze.no1271

import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.math.BigInteger

private const val IBS = 2_002
private const val OBS = 1_000
private val I = DataInputStream(System.`in`)
private val O = BufferedOutputStream(System.`out`, OBS)
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

private val NUM = 48..57
private val inBuf = StringBuilder(1_000)
private fun i(): BigInteger {
  var c: Byte
  while (r().also { c = it } in NUM || c == 45.toByte()) {
    when (c) {
      in NUM -> inBuf.append(c - 48)
      else -> inBuf.append('-')
    }
  }
  val n = inBuf.toString()
    .toBigInteger()
  inBuf.setLength(0)
  return n
}


private const val WS = 1000
private val WB = ByteArray(WS)
private fun w(s: BigInteger) {
  var pos = 0
  for (ch in s.toString()) WB[pos++] = ch.code.toByte()
  O.write(WB, 0, pos)
}

fun main() {
  val a = i()
  val b = i()
  val (q, r) = a.divideAndRemainder(b)
  w(q)
  O.write('\n'.code)
  w(r)
  O.flush()
}
