package 백준.Silver.no1463

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private val `in` = BufferedInputStream(System.`in`, 1 shl 5)
private val out = BufferedOutputStream(System.`out`, 1 shl 12)

private fun r(): Int = `in`.read()

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
  out.write(outBuf, stt, MAX_NUM_LEN - stt)
}

fun main() {
  var minCnt = 100
  fun bfs(v: Int, cnt: Int) {
    if (minCnt < cnt) return
    if (v <= 1) {
      minCnt = cnt
      return
    }
    if (v % 3 == 0) bfs(v / 3, cnt + 1)
    if (v % 2 == 0) bfs(v / 2, cnt + 1)
    bfs(v - 1, cnt + 1)
  }
  bfs(i(), 0)
  writeBy(minCnt)
  out.flush()
}
