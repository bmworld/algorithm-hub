package 백준.Silver.no10828

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private val IN = BufferedInputStream(System.`in`, 1 shl 20)
private val OUT = BufferedOutputStream(System.`out`, 1 shl 19)
private const val MAX_NUM_LEN = 6
private val buf = ByteArray(MAX_NUM_LEN + 1).also { it[MAX_NUM_LEN] = '\n'.code.toByte() }

private fun read(): Int = IN.read()

private const val PUSH = 1
private const val POP = 2
private const val TOP = 3
private const val SIZE = 4
private const val EMPTY = 5
private const val EMPTY_V = -1

fun main() {
  val n = readInt()
  val arr = IntArray(n)
  var i = 0
  repeat(n) {
    when (readWordAsCode()) {
      PUSH -> arr[i++] = readInt()
      POP -> writeln(if (i == 0) EMPTY_V else arr[--i])
      TOP -> writeln(if (i == 0) EMPTY_V else arr[i - 1])
      SIZE -> writeln(i)
      EMPTY -> writeln(if (i == 0) 1 else 0)
    }
  }
  OUT.flush()
}

private fun writeln(num: Int) {
  var x = num
  if (x < 0) {
    x = -x
    OUT.write(45)
  }
  var endIdx = MAX_NUM_LEN - 1
  do {
    buf[endIdx--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = endIdx + 1
  val len = MAX_NUM_LEN - stt + 1 // 개행 포함
  OUT.write(buf, stt, len)
}

private fun readInt(): Int {
  var c = read()
  while (c == 10 || c == 32) c = read()
  var ne = false
  if (c == 45) {
    ne = true
    c = read()
  }
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = read()
  }
  return if (ne) -n else n
}

fun readWordAsCode(): Int {
  var c = read()
  while (c == 10 || c == 32) c = read()
  return when (c) {
    116 -> {
      skipToSep()
      TOP
    }
    115 -> {
      skipToSep()
      SIZE
    }
    101 -> {
      skipToSep()
      EMPTY
    }
    112 -> {
      val c2 = read()
      skipToSep()
      if (c2 == 117) PUSH else POP
    }
    else -> readWordAsCode()
  }
}

private fun skipToSep(): Int {
  var c = read()
  while (!(c == 10 || c == 32)) c = read()
  return c
}
