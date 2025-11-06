import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private val IN = BufferedInputStream(System.`in`, 1 shl 20)
private val OUT = BufferedOutputStream(System.`out`, 1 shl 20)
private const val MAX_NUM_LEN = 6
private val buf = ByteArray(MAX_NUM_LEN + 1).also { it[MAX_NUM_LEN] = '\n'.code.toByte() }

private fun readByte(): Int = IN.read()

private const val PUSH = "push"
private const val POP = "pop"
private const val SIZE = "size"
private const val EMPTY = "empty"
private const val EMPTY_VALUE = -1
private const val TOP = "top"

fun main() {
  val n = readInt()

  val arr = IntArray(n + 1) { EMPTY_VALUE }
  var cnt = 0
  repeat(n) {
    val s = readString()
    when (s) {
      PUSH -> {
        val v = readInt()
        arr[cnt++] = v
      }
      POP -> {
        writeln(if (cnt == 0) EMPTY_VALUE else arr[--cnt])
        arr[cnt] = EMPTY_VALUE
      }
      SIZE -> writeln(cnt)
      EMPTY -> writeln(if (cnt == 0) 1 else 0)
      TOP -> writeln(if (cnt == 0) EMPTY_VALUE else arr[cnt - 1])
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
  var sign = 1
  var c = readByte()
  while (true) {
    when (c) {
      in 48..57 -> {
        n = n * 10 + (c - 48)
        c = IN.read()
      }
      10,
      13,
      32 -> return n * sign
      45 -> {
        sign = -1
        c = readByte()
      }
      else -> c = readByte()
    }
  }
}

val sb = StringBuilder(5)

fun readString(): String {
  sb.clear()
  var c = readByte()
  while (true) {
    when (c) {
      in 97..122 -> {
        sb.append(c.toChar())
        c = readByte()
      }
      10,
      13,
      32 -> return sb.toString()
      else -> c = readByte()
    }
  }
}