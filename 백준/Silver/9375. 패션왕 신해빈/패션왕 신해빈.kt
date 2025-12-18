import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 8)

private val IN: ByteArray = System.`in`.readBytes()
private var inPos = 0
private const val EOF = -1

private fun r(): Int = if (inPos < IN.size) (IN[inPos++].toInt() and 0xFF) else EOF

private fun i(): Int {
  var n = 0
  var c = r()
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = r()
  }
  return n
}


private val SEP = BooleanArray(256).also {
  it[10] = true
  it[13] = true
  it[32] = true
}

private const val WORD_MAX_LEN = 20
private val sb = StringBuilder(WORD_MAX_LEN)

fun s(): String {
  var c = r()
  while (c != EOF && SEP[c]) c = r()
  while (c > 32) {
    sb.append(c.toChar())
    c = r()
  }
  val s = sb.toString()
  sb.setLength(0)
  return s
}

private const val MAX_NUM_LEN = 20
private val outBuf = ByteArray(MAX_NUM_LEN + 1).also { it[MAX_NUM_LEN] = '\n'.code.toByte() }

private fun w(num: Int) {
  var n = num
  var end = MAX_NUM_LEN - 1
  do {
    outBuf[end--] = ((n % 10) + 48).toByte()
    n /= 10
  } while (n > 0)
  val stt = end + 1
  OUT.write(
    outBuf, stt, MAX_NUM_LEN - stt + 1 // 개행 포함
  )
}

fun main() {
  repeat(i()) {
    val m = i()
    val a = HashMap<String, Int>(m)
    repeat(m) {
      s()
      val t = s()
      val cur = a[t]
      a[t] = if (cur == null) 1 else (cur + 1)
    }
    var cnt = 1
    for (v in a.values) cnt *= v + 1
    w(cnt - 1)
  }
  OUT.flush()
}