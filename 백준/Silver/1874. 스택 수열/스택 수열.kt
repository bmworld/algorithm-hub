import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 12)
private val B: ByteArray = System.`in`.readBytes()
private var Bi = 0
private const val EOF = -1

private fun read(): Int = if (Bi < B.size) (B[Bi++].toInt() and 0xFF) else EOF

private fun readInt(): Int {
  var c = read()
  while (c != EOF && c <= 32) c = read()

  var neg = false
  if (c == '-'.code) {
    neg = true
    c = read()
  }
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = read()
  }
  return if (neg) -n else n
}

private const val OFF: Byte = 0
private const val ON: Byte = 1
private val PUSH =
    ByteArray(2).also {
      it[0] = '+'.code.toByte()
      it[1] = '\n'.code.toByte()
    }
private val POP =
    ByteArray(2).also {
      it[0] = '-'.code.toByte()
      it[1] = '\n'.code.toByte()
    }

fun main() {
  val n = readInt()
  val ch = ByteArray(n)
  val buf = ByteArrayOutputStream()
  var lastN = 0
  var top = 0
  repeat(n) {
    val v = readInt()
    if (lastN < v) {
      while (lastN < v) {
        ch[lastN++] = ON
        buf.write(PUSH)
      }

      top = lastN - 1
      ch[top] = OFF
      buf.write(POP)
      while (top > 0) if (ch[top - 1] == ON) break else top--
    } else {
      if (v != top || ch[top - 1] == OFF) {
        OUT.write('N'.code)
        OUT.write('O'.code)
        OUT.flush()
        return
      }

      ch[v - 1] = OFF
      buf.write(POP)
      while (top > 0) if (ch[top - 1] == ON) break else top--
    }
  }

  OUT.write(buf.toByteArray())
  OUT.flush()
}