import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 20_000
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
private val WB = ByteArray(WS + 1).also { it[WS] = 10 }
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
  O.write(WB, pos, WS - pos + 1)
}

private const val MAX_LEN = 100_000
fun main() {

  val r1 = IntArray(MAX_LEN)
  val r2 = IntArray(MAX_LEN)
  repeat(i()) {
    val cs = i()
    repeat(cs) {
      r1[it] = i()
    }

    repeat(cs) {
      val r2v = i()
      val r1v = r1[it]

      var r1Max = 0
      var r2Max = 0

      if (it > 0) {
        r1Max = r2[it - 1]
        r2Max = r1[it - 1]
      }

      if (it > 1) {
        val v1 = r1[it - 2]
        val v2 = r2[it - 2]
        if (v1 > v2) {
          if (v1 > r1Max) r1Max = v1
          if (v1 > r2Max) r2Max = v1
        } else {
          if (v2 > r1Max) r1Max = v2
          if (v2 > r2Max) r2Max = v2
        }
      }

      r1[it] = r1v + r1Max
      r2[it] = r2v + r2Max
    }

    val m1 = r1[cs - 1]
    val m2 = r2[cs - 1]
    w(if (m1 > m2) m1 else m2)
  }

  O.flush()
}