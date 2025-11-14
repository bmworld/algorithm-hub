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

private const val MAX_NUM_LEN = 9
private val outBuf = ByteArray(MAX_NUM_LEN)

private fun writeBy(num: Int) {
  var x = num
  var end = outBuf.size - 1
  do {
    outBuf[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = end + 1
  OUT.write(outBuf, stt, outBuf.size - stt)
}

fun main() {
  val n = i()
  val m = i()
  var i = n - 1
  var apprM = 1
  val a =
      IntArray(n) {
        val v = i()
        if (v in (apprM + 1)..m) {
          apprM = v
          i = it
        }
        v
      }

  var acc = 0
  var cnt = 0
  while (i >= 0) {
    val v = a[i]
    val nv = acc + v
    if (nv > m) {
      i--
      continue
    }
    cnt++
    acc = nv
    if (nv == m) break
  }
  writeBy(cnt)
  OUT.flush()
}