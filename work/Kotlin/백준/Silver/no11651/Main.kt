package 백준.Silver.no11651

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val CODE_0 = '0'.code
private const val CODE_9 = '9'.code
private const val CODE_MINUS = '-'.code
private const val CODE_SPACE = ' '.code
private const val CODE_NL = '\n'.code
private const val SEPARATOR = 1_000_000
private const val HALF: Long = 100_000L
private const val MAX_LEN = 6

fun main() {
  val n = readInt()
  val arr = LongArray(n)
  repeat(n) {
    val x = readInt().toLong()
    val y = readInt().toLong()
    arr[it] = (y + HALF) * SEPARATOR + (x + HALF)
  }

  arr.sort()

  for (v in arr) {
    val y = v / SEPARATOR - HALF
    val x = v % SEPARATOR - HALF
    writeln(x)
    OUT.write(CODE_SPACE)
    writeln(y)
    OUT.write(CODE_NL)
  }

  OUT.flush()
}

val IN = BufferedInputStream(System.`in`, 1 shl 20)
val OUT = BufferedOutputStream(System.`out`, 1 shl 20)

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var n = 0
  var sign = 1
  if (c == CODE_MINUS) {
    sign = -1
    c = IN.read()
  }

  while (c in CODE_0..CODE_9) {
    n = n * 10 + (c - CODE_0)
    c = IN.read()
  }
  return n * sign
}

private val buf = ByteArray(MAX_LEN)

private fun writeln(num: Long) {
  var x = num
  if (x < 0) {
    OUT.write(CODE_MINUS)
    x = -x
  }
  var endIdx = MAX_LEN - 1
  do {
    buf[endIdx--] = ((x % 10) + CODE_0).toByte()
    x /= 10
  } while (x > 0)
  val stt = endIdx + 1
  val len = MAX_LEN - stt

  OUT.write(buf, stt, len)
}
