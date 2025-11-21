package 백준.Bronze.no2588

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 11)
private val IN = BufferedInputStream(System.`in`)
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

private const val MAX_NUM_LEN = 6
private val outBuf = ByteArray(MAX_NUM_LEN + 1).also { it[MAX_NUM_LEN] = '\n'.code.toByte() }

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
    outBuf, stt, MAX_NUM_LEN - stt + 1 // + ln
  )
}

fun main() {
  val arr = ByteArray(3)
  val a = i()
  var b = 0
  var i = 0
  var c = r()
  while (c in 48..57) {
    b = b * 10 + (c - 48)
    arr[i++] = (c - 48).toByte()
    c = r()
  }
  for (i in 2 downTo 0) w(a * arr[i])
  w(a * b)
  OUT.flush()
}
