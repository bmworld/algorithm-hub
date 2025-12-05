import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 8
private const val OBS = 4
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
  var v = num
  var end = WS - 1
  do {
    WB[end--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  val stt = end + 1
  O.write(WB, stt, -stt + WS)
}

fun main() {
  val n = i()
  val r = i()
  val c = i()

  var cnt = 0
  var len = 1 shl n
  var vol = len * len / 4
  var sDist = len / 4
  var sr = len / 2 - 1
  var sc = sr

  repeat(n) {
    var bigger = 0
    if (c > sc) {
      bigger += 1
      sc += sDist
    } else sc -= sDist

    if (r > sr) {
      bigger += 2
      sr += sDist
    } else sr -= sDist

    cnt += vol * bigger
    len /= 2
    vol /= 4
    sDist /= 2
  }

  w(cnt)
  O.flush()
}