import java.io.BufferedOutputStream

private val out = BufferedOutputStream(System.`out`, 1 shl 11)

private val iBytes: ByteArray = System.`in`.readBytes()
private var iPos = 0
private const val EOF = -1

private fun r(): Int = if (iPos < iBytes.size) (iBytes[iPos++].toInt() and 0xFF) else EOF

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
  out.write(outBuf, stt, MAX_NUM_LEN - stt)
}

fun main() {
  val v = i()
  var cnt = 100

  fun bfs(v: Int, dep: Int) {
    if (v < 1 || dep > cnt) return
    if (v == 1) {
      if (dep < cnt) cnt = dep
      return
    }

    if (v % 3 == 0) bfs(v / 3, dep + 1)
    if (v % 2 == 0) bfs(v / 2, dep + 1)
    bfs(v - 1, dep + 1)
  }

  bfs(v, 0)
  writeBy(cnt)
  out.flush()
}