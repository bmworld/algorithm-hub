import java.io.BufferedOutputStream

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

private const val MAX_NUM_LEN = 2
private val buf = ByteArray(MAX_NUM_LEN)

private fun writeln(num: Int) {
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
  OUT.write(buf, stt, MAX_NUM_LEN - stt)
}

fun main() {
  val n = readInt()
  val a = IntArray(n)
  val ext_half = Math.round(n.toDouble() * 0.15).toInt()

  repeat(n) { a[it] = readInt() }
  a.sort()

  var sum = 0.0
  for (i in ext_half until n - ext_half) sum += a[i]
  val avg = Math.round(sum / (n - (2 * ext_half))).toInt()
  writeln(avg)
  OUT.flush()
}