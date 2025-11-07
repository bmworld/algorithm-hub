import java.io.BufferedOutputStream

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

private val OUT = BufferedOutputStream(System.`out`, 1 shl 12)
private const val MAX_NUM_LEN = 4
private val buf =
    ByteArray(MAX_NUM_LEN + 2).also {
      it[MAX_NUM_LEN] = ','.code.toByte()
      it[MAX_NUM_LEN + 1] = ' '.code.toByte()
    }

private fun writeln(num: Int, last: Boolean) {
  var x = num
  var neg = false
  if (x < 0) {
    neg = true
    x = -x
  }
  var end = MAX_NUM_LEN - 1
  do {
    buf[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  if (neg) buf[end--] = 45
  val stt = end + 1
  OUT.write(
      buf,
      stt,
      MAX_NUM_LEN - stt + if (last) 0 else 2, // 구분자(`, `) 포함여부
  )
}

fun main() {
  val n = readInt()
  val k = readInt()
  val ch = BooleanArray(n)
  OUT.write('<'.code)
  var i = -1
  var cnt = 0
  while (cnt < n) {
    var turn = 0
    while (turn < k) {
      i = if (i + 1 == n) 0 else i + 1
      if (!ch[i]) turn++
    }
    cnt++
    ch[i] = true
    writeln(i + 1, cnt == n)
  }
  OUT.write('>'.code)
  OUT.flush()
}