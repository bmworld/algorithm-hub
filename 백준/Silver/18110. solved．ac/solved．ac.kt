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
  val a = IntArray(30)
  repeat(n) { a[readInt() - 1]++ }

  val ext = (n * 15 + 50) / 100 // round
  val rem = n - (2 * ext)

  var sum = 0.0
  var excluded = ext
  var added = rem
  for (score in 1..30) {
    var cnt = a[score - 1]
    if (cnt == 0) continue
    if (excluded > 0) {
      val nc = if (cnt > excluded) cnt - excluded else 0
      excluded -= cnt
      cnt = nc
    }

    if (cnt == 0) continue
    if (added > 0) {
      sum += score * (if (cnt < added) cnt else added)
      added -= cnt
      if (added <= 0) break
    }
  }
  val avg = ((sum + rem / 2) / rem).toInt()
  writeln(avg)
  OUT.flush()
}