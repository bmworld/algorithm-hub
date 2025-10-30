package 백준.Silver.no2751

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val maxNumSize = 7 // 부호제외
private val buf = ByteArray(maxNumSize + 1).also { it[7] = '\n'.code.toByte() }

fun main() {

  val n = readInt()
  val half = 1_000_000
  val arr = BooleanArray(half * 2 + 1)
  var max = Int.MIN_VALUE
  var min = Int.MAX_VALUE
  repeat(n) {
    val v = readInt()
    val i = v + half
    if (i > max) max = i
    if (i < min) min = i
    arr[i] = true
  }
  for (i in min..max) {
    if (arr[i]) {
      var num = i - half
      if (num < 0) {
        OUT.write('-'.code) // 부호처리
        num = -num
      }

      var x = num
      var end = 6
      do {
        buf[end--] = ((x % 10) + 48).toByte()
        x /= 10
      } while (x > 0)
      val stt = end + 1
      val len = maxNumSize - stt + 1 // 개행 포함
      OUT.write(buf, stt, len)
    }
  }
  OUT.flush()
}

val IN = BufferedInputStream(System.`in`, 1 shl 20)
val OUT = BufferedOutputStream(System.`out`, 1 shl 22)

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var sign = 1
  if (c == '-'.code) { // 부호 처리
    sign = -1
    c = IN.read()
  }
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = IN.read()
  }
  return n * sign
}
