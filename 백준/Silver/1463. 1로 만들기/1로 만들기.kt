import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 11)
private val IN = BufferedInputStream(System.`in`, 1 shl 11)

private fun r(): Int = IN.read()

private fun i(): Int {
  var n = 0
  var c = r()
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = r()
  }
  return n
}

private const val MAX_NUM_LEN = 4
private val outBuf = ByteArray(MAX_NUM_LEN)

private fun writeBy(num: Int) {
  var x = num
  var end = MAX_NUM_LEN - 1
  do {
    outBuf[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = end + 1
  OUT.write(outBuf, stt, MAX_NUM_LEN - stt)
}

fun main() {
  val v = i()
  var best = 1000

  fun op(
      v: Int,
      cnt: Int,
  ) {
    if (v <= 1) {
      if (best > cnt) best = cnt
      return
    }
    val vBy3 = v / 3
    val rBy3 = v % 3
    val vBy2 = v / 2
    val rBy2 = v % 2
    op(vBy3, cnt + rBy3 + 1)
    op(vBy2, cnt + rBy2 + 1)
  }

  writeBy(
      if (v <= 1) 0
      else {
        op(v, 0)
        best
      }
  )
  OUT.flush()
}