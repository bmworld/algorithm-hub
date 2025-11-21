import java.io.BufferedOutputStream
import java.io.DataInputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 12)
private const val INBufSize = 1 shl 14
private val IN = DataInputStream(System.`in`)
private val INBuf = ByteArray(INBufSize)
private const val EOF = -1
private var INPos = 0
private var INLen = 0

private fun r(): Byte {
  if (INPos == INLen) {
    INLen = IN.read(INBuf, 0, INBufSize)
    if (INLen == EOF) INBuf[0] = EOF.toByte()
    INPos = 0
  }
  return INBuf[INPos++]
}

private fun i(): Int {
  var n = 0
  var c: Byte
  while (r().also { c = it } in 48..57) n = n * 10 + (c - 48)
  return n
}

private const val MAX_NUM_LEN = 10
private val outBuf = ByteArray(MAX_NUM_LEN)

private fun w(
  num: Int,
) {
  var x = num
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

fun main() {
  val n = i()
  val goal = i()
  val a = IntArray(n)
  var l = 0
  var r = 0
  repeat(n) {
    val v = i()
    a[it] = v
    if (v > r) r = v
  }

  var max = 0
  while (l <= r) {
    var sum = 0L
    val m = (l + r) / 2
    for (v in a) {
      if (v <= m) continue
      sum += v - m
      if (sum >= goal) break
    }
    if (sum >= goal) {
      max = m
      l = m + 1
    } else {
      r = m - 1
    }
  }

  w(max)
  OUT.flush()
}