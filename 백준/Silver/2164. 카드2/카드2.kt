import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.util.*

val IN = BufferedInputStream(System.`in`, 1 shl 20)
val OUT = BufferedOutputStream(System.`out`, 1 shl 20)
private val buf = ByteArray(6)

fun main() {
  val n = readInt()
  val Q = LinkedList<Int>().also { for (i in 1..n) it.add(i) }

  var i = 1
  while (Q.size > 1) {
    val v = Q.poll()
    if (i == -1) Q.add(v)
    i = -i
  }

  writeln(Q.pop())
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