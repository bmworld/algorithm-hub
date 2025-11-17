import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 12)

private val IN: ByteArray = System.`in`.readBytes()
private var inPos = 0
private const val EOF = -1

private fun r(): Int = if (inPos < IN.size) (IN[inPos++].toInt() and 0xFF) else EOF

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

private fun w(num: Int) {
  var n = num
  var end = MAX_NUM_LEN - 1
  do {
    outBuf[end--] = ((n % 10) + 48).toByte()
    n /= 10
  } while (n > 0)
  val stt = end + 1
  OUT.write(outBuf, stt, MAX_NUM_LEN - stt)
}

private const val CHECK = 1.toByte()

fun main() {
  val len = i()
  val ch = ByteArray(len)
  val arr = Array(len) { mutableListOf<Int>() }
  repeat(i()) {
    val l = i()
    val r = i()
    arr[l - 1].add(r)
    arr[r - 1].add(l)
  }
  var cnt = 0
  fun bfs(i: Int) {
    ch[i] = CHECK
    if (i != 0) cnt++
    for (n in arr[i]) if (ch[n - 1] != CHECK) bfs(n - 1)
  }
  bfs(0)
  w(cnt)
  OUT.flush()
}