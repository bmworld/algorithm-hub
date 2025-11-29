import java.io.BufferedOutputStream

private const val OBS = 1 shl 4
private val O = BufferedOutputStream(System.`out`, OBS)
private const val WS = 4
private val WB = ByteArray(WS)

private fun w(
  num: Int,
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

private const val SEP = '\n'.code

private val ID = ByteArray(7).also {
  it[0] = 98
  it[1] = 109
  it[2] = 119
  it[3] = 111
  it[4] = 114
  it[5] = 108
  it[6] = 100
}


fun main() {
  w(151)
  O.write(SEP)
  O.write(ID)
  O.flush()
}