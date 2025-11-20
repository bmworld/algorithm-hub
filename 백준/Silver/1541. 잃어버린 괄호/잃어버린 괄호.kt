import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 16)

private val IN: ByteArray = System.`in`.readBytes()
private var inPos = 0
private const val EOF = -1

private fun r(): Int = if (inPos < IN.size) (IN[inPos++].toInt() and 0xFF) else EOF
private const val MAX_NUM_LEN = 6
private val outBuf = ByteArray(MAX_NUM_LEN)

private fun w(
  num: Int,
) {
  var x = num
  if (x < 0) {
    OUT.write(MINUS)
    x = -x
  }
  var end = MAX_NUM_LEN - 1
  do {
    outBuf[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = end + 1

  OUT.write(
    outBuf, stt, MAX_NUM_LEN - stt
  )
}

private const val PLUS = 43
private const val MINUS = 45
private var wonKiOk = false
private var total = 0
private var tmp = 0
private var n = 0

fun submitN() {
  if (wonKiOk) tmp += n else total += n
  n = 0
}

fun useWonKiOk() {
  submitN()
  total -= tmp
  tmp = 0
  wonKiOk = true
}

fun main() {
  while (true) {
    var c = r()
    while (c >= -1) {
      when (c) {
        EOF -> {
          submitN()
          w(total - tmp)
          OUT.flush()
          return
        }

        MINUS -> useWonKiOk()
        PLUS -> submitN()

        in 48..57 -> {
          n = n * 10 + (c - 48)
        }
      }
      c = r()
    }
  }
}