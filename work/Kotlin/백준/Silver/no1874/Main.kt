package 백준.Silver.no1874

import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 11)
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
  val buf = ByteArrayOutputStream()
  val n = readInt()
  val stack = IntArray(n)
  var i = 0
  var lastN = 0
  repeat(n) {
    val v = readInt()
    if (v > lastN) {
      while (v > lastN) {
        stack[i++] = ++lastN
        buf.write(PUSH)
      }

      stack[--i] = 0
      buf.write(POP)
    } else {

      val pop = if (i > 0) stack[i - 1] else 0
      if (v == pop) {
        stack[i-- - 1] = 0
        buf.write(POP)
      } else {
        OUT.write('N'.code)
        OUT.write('O'.code)
        OUT.flush()
        return
      }
    }
  }
  OUT.write(buf.toByteArray())
  OUT.flush()
}
