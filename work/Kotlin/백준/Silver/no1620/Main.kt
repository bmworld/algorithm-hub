package 백준.Silver.no1620

import java.io.BufferedOutputStream

private val out = BufferedOutputStream(System.`out`, 1 shl 12)
private val `in`: ByteArray = System.`in`.readBytes()
private var inPos = 0
private const val EOF = -1
private val isNum = 48..57

private fun r(): Int = if (inPos < `in`.size) (`in`[inPos++].toInt() and 0xFF) else EOF

private fun i(v: Int?): Int {
  var n = 0
  var c = v ?: r()
  while (c in isNum) {
    n = n * 10 + (c - 48)
    c = r()
  }
  return n
}

private val inSBuf = StringBuilder(20)

private fun s(v: Int?): String {
  var c = v ?: r()
  while (c >= 65) {
    inSBuf.append(c.toChar())
    c = r()
  }
  val s = inSBuf.toString()
  inSBuf.setLength(0)
  return s
}

private const val NL_CODE = '\n'.code
private const val MAX_NUM_LEN = 6
private val outIBuf = ByteArray(MAX_NUM_LEN + 1).also { it[MAX_NUM_LEN] = NL_CODE.toByte() }

private fun writeBy(num: Int) {
  var x = num
  var end = MAX_NUM_LEN - 1
  do {
    outIBuf[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = end + 1
  out.write(
      outIBuf,
      stt,
      MAX_NUM_LEN - stt + 1, // 개행 포함
  )
}

fun main() {
  val n = i(null)
  val m = i(null)
  val byName = HashMap<String, Int>(n)
  val byNum =
      Array(n) {
        val v = s(null)
        byName[v] = it + 1
        v + "\n"
      }
  repeat(m) {
    val c = r()
    if (c in isNum) {
      out.write(byNum[i(c) - 1].toByteArray())
    } else writeBy(byName[s(c)]!!)
  }
  out.flush()
}
