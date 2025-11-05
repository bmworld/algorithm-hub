import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private val IN = BufferedInputStream(System.`in`, 1 shl 15)
private val OUT = BufferedOutputStream(System.`out`, 1 shl 15)

private val Y = "YES\n".toByteArray()
private val N = "NO\n".toByteArray()

fun main() {
  val n = readInt()
  repeat(n) { OUT.write(if (readLine()) Y else N) }

  OUT.flush()
}

private fun readInt(): Int {
  var n = 0
  var sign = 1
  var c = IN.read()
  while (c !in 48..57) c = IN.read()
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
        c = IN.read()
      }
      else -> c = IN.read()
    }
  }
}

private fun readByte(): Int = IN.read()

private fun readLine(): Boolean {
  var cnt = 0
  var c = readByte()
  while (true) {
    when (c) {
      40, // (
      -> {
        cnt++
        c = readByte()
      }
      41, // )
      -> {
        cnt--
        if (cnt < 0) {
          skipLine()
          return false
        }

        c = readByte()
      }
      10,
      13 -> return cnt == 0
      else -> c = readByte()
    }
  }
}

private fun skipLine() {
  var c = readByte()
  while (c != -1 && c != 10 && c != 13) c = readByte()
}