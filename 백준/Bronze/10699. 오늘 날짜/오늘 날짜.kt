import java.io.BufferedOutputStream

private const val OBS = 1 shl 4
private val O = BufferedOutputStream(System.`out`, OBS)
private const val WS = 4
private val WB = ByteArray(WS)

private fun w(
  num: Long,
) {
  var x = num
  var end = WS - 1
  do {
    WB[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = end + 1
  O.write(WB, stt, WS - stt)
}

private const val SEP = '-'.code

fun main() {
  w(2025)
  O.write(SEP)
  w(11)
  O.write(SEP)
  w(29)
  O.flush()
}