import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private val IN = BufferedInputStream(System.`in`, 1 shl 5)
private val OUT = BufferedOutputStream(System.`out`, 1 shl 11)

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

private const val MAX_NUM_LEN = 3
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
  writeBy(DP[i()])
  OUT.flush()
}

private const val MAX = 1_000_000
private val DP =
    IntArray(MAX + 1).also {
      it[1] = 0
      it[2] = 1
      it[3] = 1
      for (i in 4..MAX) {
        var min = it[i - 1]
        if (i % 3 == 0 && min > it[i / 3]) min = it[i / 3]
        if (i % 2 == 0 && min > it[i / 2]) min = it[i / 2]

        it[i] = min + 1
      }
    }