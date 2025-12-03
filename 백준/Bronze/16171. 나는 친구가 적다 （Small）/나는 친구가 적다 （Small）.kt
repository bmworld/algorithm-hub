import java.io.BufferedOutputStream

private const val OBS = 1
private val O = BufferedOutputStream(System.`out`, OBS)

private val NUM = 48..57

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

fun main() {
  val r = readlnOrNull()!!
  val k = readlnOrNull()!!
  w(if (r.filter { c -> c.code.toByte() !in NUM }
      .contains(k)) 1 else 0)
  O.flush()
}