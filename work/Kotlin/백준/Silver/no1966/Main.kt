package 백준.Silver.no1966

import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 6)
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

private const val MAX_NUM_LEN = 3
private val buf = ByteArray(MAX_NUM_LEN + 1).also { it[MAX_NUM_LEN] = '\n'.code.toByte() }

private fun writeln(num: Int) {
  var x = num
  var end = MAX_NUM_LEN - 1
  do {
    buf[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = end + 1
  OUT.write(
      buf,
      stt,
      MAX_NUM_LEN - stt + 1, // 개행 포함
  )
}

private const val USED: Byte = 1

fun main() {
  val a = IntArray(100)
  val maxNum = 9

  repeat(readInt()) {
    val len = readInt()
    val ti = readInt()
    val cnt = IntArray(maxNum)
    val ch = ByteArray(len)
    var maxHv = 1
    repeat(len) {
      val v = readInt()
      a[it] = v
      cnt[v - 1]++
      if (v > maxHv) maxHv = v
    }

    var i = 0
    var order = 0
    while (true) {
      if (order == len) break
      // 커서 이동
      while (ch[i] == USED) i = if (i + 1 < len) i + 1 else 0
      val v = a[i]
      if (v == maxHv) {
        order++
        ch[i] = USED
        cnt[v - 1]--
        // 최우선순위 조정
        if (cnt[v - 1] == 0) {
          var next = v - 1
          while (next >= 1) {
            if (cnt[next - 1] > 0) {
              maxHv = next
              break
            }
            next--
          }
        }
        if (i == ti) {
          writeln(order)
          break
        }
      }
      i = if (i + 1 < len) i + 1 else 0
    }
  }
  OUT.flush()
}
