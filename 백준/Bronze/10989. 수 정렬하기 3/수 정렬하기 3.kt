import java.io.BufferedInputStream
import java.io.BufferedOutputStream

fun main() {
  val arr = IntArray(10001) // 1 <= v <= 10,000
  val n = readInt()
  var max = 0
  repeat(n) {
    val v = readInt()
    arr[v]++
    if (v > max) max = v
  }

  val buf = ByteArray(6) // 최대 5자리 + 개행

  for (v in 1..max) {
    var cnt = arr[v]
    if (cnt == 0) continue
    val len = fillBuf(buf, v)
    while (cnt-- > 0) OUT.write(buf, 0, len)
  }
  OUT.flush()
}

fun fillBuf(buf: ByteArray, v: Int): Int {
  var num = v // Int -> byte
  var i = 0
  do {
    buf[i++] = (num % 10 + '0'.code).toByte()
    num /= 10
  } while (num > 0)
  // 뒤집기
  var e = i - 1
  var s = 0
  while (s < e) {
    val tmp = buf[e]
    buf[e] = buf[s]
    buf[s] = tmp
    s++
    e--
  }
  buf[i++] = '\n'.code.toByte()
  return i
}

val IN = BufferedInputStream(System.`in`)
val OUT = BufferedOutputStream(System.`out`)

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read() // filter
  var n = 0
  while (c in '0'.code..'9'.code) {
    n = n * 10 + (c - '0'.code)
    c = IN.read()
  }
  return n
}