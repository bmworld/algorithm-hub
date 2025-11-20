package 백준.Silver.no1541

import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 10)

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
private val DIGIT = 48..57
private var subMode = false
private var total = 0
private var n = 0

fun main() {
  var c = r()
  while (c != EOF) {
    when (c) {
      in DIGIT -> n = n * 10 + (c - 48)

      PLUS -> {
        if (subMode) total -= n else total += n
        n = 0
      }

      MINUS -> {
        if (subMode) total -= n else total += n
        n = 0
        subMode = true
      }
    }
    c = r()
  }

  if (subMode) total -= n else total += n
  w(total)
  OUT.flush()
}
