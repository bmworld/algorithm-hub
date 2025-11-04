package 백준.Silver.no2164

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

val IN = BufferedInputStream(System.`in`, 1 shl 20)
val OUT = BufferedOutputStream(System.`out`, 1 shl 20)
private val buf = ByteArray(6)
private const val ODD = 1
private const val EVEN = 2

fun main() {
  var n = readInt()
  var top = 1
  var diff = 1
  var t = ODD // 삭제 대상

  while (n > 1) {
    if (t == ODD) top += diff
    val nt =
        if (t == ODD) {
          if (n % 2 == ODD) EVEN else ODD
        } else {
          if (n % 2 == ODD) ODD else EVEN
        }

    n = if (t == ODD) n / 2 else (n + 1) / 2
    t = nt
    diff *= 2
  }

  writeln(top)
  OUT.flush()
}

private fun readInt(): Int {
  var n = 0
  var sign = 1
  var c = IN.read()
  while (true) {
    when (c) {
      in 48..57 -> {
        n = n * 10 + (c - 48)
        c = IN.read()
      }
      45 -> {
        sign = -1
        c = IN.read()
      }
      10,
      32 -> return n * sign
      else -> c = IN.read()
    }
  }
}

private fun writeln(num: Int) {
  var x = num
  var endIdx = buf.size - 1
  do {
    buf[endIdx--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = endIdx + 1
  val len = buf.size - stt
  OUT.write(buf, stt, len)
}

// 32: SPACE
// 10: LF / New Line
// 45: Minus
// 48..57: 0-9
// 65..90: A-Z
// 97..122: a-z
