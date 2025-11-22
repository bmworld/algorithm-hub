import java.io.BufferedOutputStream

private const val IBS = 1_000_000
private const val OBS = 1 shl 3
private val O = BufferedOutputStream(System.`out`, OBS)
private val IB = ByteArray(IBS)
private const val WS = 7
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

  O.write(
    WB, stt, WS - stt
  )
}


private const val SPACE = 32.toByte()

fun main() {
  val len = System.`in`.read(IB)
  var inWord = false
  var i = 0
  var cnt = 0
  while (i < len) {
    val isWord = IB[i++] > SPACE
    if (!inWord && isWord) cnt++
    inWord = isWord
  }
  w(cnt)
  O.flush()
}