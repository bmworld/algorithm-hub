package 백준.Silver.no17219

import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 16)

private val inBuf: ByteArray = System.`in`.readBytes()
private var inPos = 0
private const val EOF = -1

private fun r(): Int = if (inPos < inBuf.size) (inBuf[inPos++].toInt() and 0xFF) else EOF

private fun i(): Int {
  var n = 0
  var c = r()
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = r()
  }
  return n
}

private val SEP =
    BooleanArray(256).also {
      it[10] = true
      it[13] = true
      it[32] = true
    }

private val inSb = StringBuilder(50)

private fun s(): String {
  var c = r()
  while (c >= 45) {
    inSb.append(c.toChar())
    c = r()
  }
  val s = inSb.toString()
  inSb.setLength(0)
  return s
}

private const val PW_MAX_LEN = 20
private const val NL_CODE = '\n'.code.toByte()
private val pwBuf = ByteArray(PW_MAX_LEN + 1)

private fun b(): ByteArray {
  var c = r()
  var len = 0
  while (c in 65..90) {
    pwBuf[len++] = c.toByte()
    c = r()
  }
  pwBuf[len++] = NL_CODE
  return pwBuf.copyOf(len)
}

fun main() {
  val n = i()
  val m = i()
  val a = HashMap<String, ByteArray>(n)
  repeat(n) {
    val url = s()
    a[url] = b()
  }
  repeat(m) { OUT.write(a[s()]!!) }
  OUT.flush()
}
