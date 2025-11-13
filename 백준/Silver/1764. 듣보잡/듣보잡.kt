import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 11)
private val B: ByteArray = System.`in`.readBytes()
private var Bi = 0
private const val EOF = -1

private fun r(): Int = if (Bi < B.size) (B[Bi++].toInt() and 0xFF) else EOF

private fun i(): Int {
  var c = r()
  while (c != EOF && c <= 32) c = r()

  var neg = false
  if (c == '-'.code) {
    neg = true
    c = r()
  }
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = r()
  }
  return if (neg) -n else n
}

private val SEP =
    BooleanArray(256).also {
      it[10] = true
      it[13] = true
      it[32] = true
    }

private const val WORD_MAX_LEN = 20
private val sb = StringBuilder(WORD_MAX_LEN)

fun s(): String {
  sb.clear()
  var c = r()
  while (c != EOF && SEP[c]) c = r()
  while (c > 32) {
    sb.append(c.toChar())
    c = r()
  }
  return sb.toString()
}

private const val NUM_MAX_LEN = 6
private val numBuf = ByteArray(NUM_MAX_LEN + 1).also { it[NUM_MAX_LEN] = '\n'.code.toByte() }

private fun write(num: Int) {
  var x = num
  var neg = false
  if (x < 0) {
    neg = true
    x = -x
  }
  var end = NUM_MAX_LEN - 1
  do {
    numBuf[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  if (neg) numBuf[end--] = 45
  val stt = end + 1
  OUT.write(
      numBuf,
      stt,
      NUM_MAX_LEN - stt + 1, // 개행포함
  )
}

fun main() {
  val n = i()
  val m = i()
  val map = HashMap<String, Boolean>(n)
  val a = ArrayList<String>()
  var cnt = 0
  repeat(n) { map[s()] = false }
  repeat(m) {
    val s = s()
    if (map[s] == false) {
      a.add(s)
      cnt++
    }
  }
  a.sort()
  write(cnt)
  for (b in a) {
    OUT.write(b.toByteArray())
    OUT.write('\n'.code)
  }
  OUT.flush()
}
