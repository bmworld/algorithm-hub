import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 20_000
private const val OBS = 9
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

private const val WS = 7
private val WB = ByteArray(WS + 2).also {
  it[WS] = 37.toByte()
  it[WS + 1] = 10.toByte()
}

private fun w(
  fiveDigits: Int,
) {
  var end = WS - 1
  var int = fiveDigits / 1000
  var decimal = fiveDigits % 1000
  repeat(3) {
    WB[end--] = (decimal % 10 + 48).toByte()
    decimal /= 10
  }

  WB[end--] = '.'.code.toByte()

  do {
    WB[end--] = (int % 10 + 48).toByte()
    int /= 10
  } while (int > 0)
  val stt = end + 1
  O.write(WB, stt, -stt + WS + 2)
}


fun main() {

  repeat(i()) {
    val n = i()
    var min = 100
    var max = 0
    var sum = 0
    val scoreCnt = IntArray(101)
    repeat(n) {
      val v = i()
      sum += v
      scoreCnt[v]++
      if (v < min) min = v
      if (v > max) max = v
    }

    var cnt = 0
    val avg = sum / n
    repeat(max - avg) { i ->
      val v = avg + 1 + i
      cnt += scoreCnt[v]
    }
    w(cnt.to5DigitRate(n))
  }
  O.flush()
}

private fun Int.to5DigitRate(
  total: Int,
): Int = (this * 10_000 * 100 / total + 5) / 10