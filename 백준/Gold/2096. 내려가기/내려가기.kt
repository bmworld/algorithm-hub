import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 45_000
private const val OBS = 1_000
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
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
private fun i(): Int {
  var v = 0
  var s = 1
  var c: Byte
  while (r().also { c = it } in NUM || c == 45.toByte()) {
    when (c) {
      in NUM -> v = v * 10 + c - 48
      else -> s = -1
    }
  }
  return s * v
}


private const val WS = 10
private val WB = ByteArray(WS)
private fun w(
  num: Int,
) {
  var v = if (num >= 0) num
  else {
    O.write(45)
    -num
  }
  var pos = WS - 1
  do {
    WB[pos--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  pos++
  O.write(WB, pos, WS - pos)
}


fun main() {
  val n = i()
  val M = IntArray(3)
  val m = IntArray(3)

  repeat(n) {
    val v1 = i()
    val v2 = i()
    val v3 = i()

    val M0 = M[0]
    val M1 = M[1]
    val M2 = M[2]

    M[0] = v1 + if (M0 > M1) M0 else M1
    M[1] = v2 + if (M0 >= M1 && M0 >= M2) M0 else if (M1 >= M0 && M1 > M2) M1 else M2
    M[2] = v3 + if (M1 > M2) M1 else M2

    val m0 = m[0]
    val m1 = m[1]
    val m2 = m[2]

    m[0] = v1 + if (m0 < m1) m0 else m1
    m[1] = v2 + if (m0 <= m1 && m0 <= m2) m0 else if (m1 <= m0 && m1 < m2) m1 else m2
    m[2] = v3 + if (m1 < m2) m1 else m2

  }

  val M0 = M[0]
  val M1 = M[1]
  val M2 = M[2]

  val m0 = m[0]
  val m1 = m[1]
  val m2 = m[2]

  w(if (M0 >= M1 && M0 >= M2) M0 else if (M1 >= M0 && M1 > M2) M1 else M2)
  O.write(32)
  w(if (m0 <= m1 && m0 <= m2) m0 else if (m1 <= m0 && m1 < m2) m1 else m2)
  O.flush()
}