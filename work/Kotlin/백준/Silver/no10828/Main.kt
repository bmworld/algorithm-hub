package 백준.Silver.no10828

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private val IN = BufferedInputStream(System.`in`, 1 shl 20)
private val OUT = BufferedOutputStream(System.`out`, 1 shl 20)
private const val MAX_NUM_LEN = 6
private val buf = ByteArray(MAX_NUM_LEN + 1).also { it[MAX_NUM_LEN] = '\n'.code.toByte() }

private fun readByte(): Int = IN.read()

private const val PUSH = 1
private const val POP = 2
private const val TOP = 3
private const val SIZE = 4
private const val EMPTY = 5
private const val EMPTY_V = -1

fun main() {
  val n = readInt()

  val arr = IntArray(n) { EMPTY_V }
  var cnt = 0
  repeat(n) {
    val c = readWordAsCode()
    when (c) {
      PUSH -> {
        val v = readInt()
        arr[cnt++] = v
      }
      POP -> {
        writeln(if (cnt == 0) EMPTY_V else arr[--cnt])
        arr[cnt] = EMPTY_V
      }
      TOP -> writeln(if (cnt == 0) EMPTY_V else arr[cnt - 1])
      SIZE -> writeln(cnt)
      EMPTY -> writeln(if (cnt == 0) 1 else 0)
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
  var n = 0
  var c = readByte()
  while (true) {
    when (c) {
      in 48..57 -> {
        n = n * 10 + (c - 48)
        c = IN.read()
      }
      10,
      13,
      32 -> return n
      else -> c = readByte()
    }
  }
}

fun readWordAsCode(): Int {
  var c = readByte()
  var cnt = 0
  var code = 0
  var firstC = 0
  while (true) {
    when (c) {
      in 97..122 -> {
        if (cnt == 0) {
          when (c) {
            116 -> code = TOP
            115 -> code = SIZE
            101 -> code = EMPTY
          }
          firstC = c
          cnt++
        } else if (firstC == 112 && cnt == 1) {
          when (c) {
            117 -> code = PUSH
            111 -> code = POP
          }
          cnt++
        }
        c = readByte()
      }
      10,
      13,
      32 -> return code
      else -> c = readByte()
    }
  }
}
