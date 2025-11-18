package 백준.Silver.no9095

import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 11)

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
private val outBuf = ByteArray(MAX_NUM_LEN + 1).also { it[MAX_NUM_LEN] = '\n'.code.toByte() }

private fun w(num: Int) {
  var n = num
  var end = MAX_NUM_LEN - 1
  do {
    outBuf[end--] = ((n % 10) + 48).toByte()
    n /= 10
  } while (n > 0)
  val stt = end + 1
  OUT.write(
    outBuf, stt, MAX_NUM_LEN - stt + 1 // 개행 포함
  )
}


fun main() {
  repeat(i()) {
    w(CNT[i()])
  }
  OUT.flush()
}


private val CNT = IntArray(12).also {
  val a = IntArray(3)
  fun dfs(
    acc: Int,
    t: Int,
  ) {
    if (acc > t) return
    else if (acc == t) {
      it[t]++
      return
    }

    for (n in 1..3) {
      a[n - 1]++
      dfs(acc + n, t)
      a[n - 1]--
    }
  }
  for (n in 1..11) dfs(0, n)
}
