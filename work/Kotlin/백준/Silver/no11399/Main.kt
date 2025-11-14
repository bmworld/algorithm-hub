package 백준.Silver.no11399

import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 11)

private val B: ByteArray = System.`in`.readBytes()
private var Bi = 0
private const val EOF = -1

private fun r(): Int = if (Bi < B.size) (B[Bi++].toInt() and 0xFF) else EOF

private fun i(): Int {
  var n = 0
  var c = r()
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = r()
  }
  return n
}

private const val MAX_NUM_LEN = 9
private val outBuf = ByteArray(MAX_NUM_LEN)

private fun writeBy(num: Int) {
  var x = num
  var end = outBuf.size - 1
  do {
    outBuf[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = end + 1
  OUT.write(outBuf, stt, outBuf.size - stt)
}

private const val MAX_TIME = 1_000

fun main() {
  val n = i()
  val a = IntArray(MAX_TIME)
  var min = MAX_TIME
  var max = 1
  repeat(n) {
    val t = i()
    a[t - 1]++
    if (t < min) min = t
    if (t > max) max = t
  }
  var total = 0
  var acc = 0
  for (t in min..max) {
    var cnt = a[t - 1]
    if (cnt == 0) continue
    while (cnt-- > 0) {
      acc += t
      total += acc
    }
  }
  writeBy(total)
  OUT.flush()
}
