package 백준.Silver.no1966

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

fun main() {
  val a = IntArray(100)
  val maxNum = 9
  val CNT = IntArray(maxNum)
  repeat(readInt()) {
    repeat(maxNum) { CNT[it] = 0 }
    val len = readInt()
    val tIdx = readInt()
    var t = 0

    var min = 9
    var max = 1
    repeat(len) {
      val v = readInt()
      a[it] = v
      CNT[v - 1]++
      if (it == tIdx) t = v
      if (v < min) min = v
      if (v > max) max = v
    }

    var p = 0
    var lastPIdx = -1

    var order = 0
    for (num in max downTo min) {
      var cnt = CNT[num - 1]
      if (num < t) break
      var setCount = false
      while (cnt > 0) {
        val v = a[p]
        if (lastPIdx == -1 || p == lastPIdx) setCount = true
        if (v == num) {
          if (setCount) {
            order++
            cnt--
          }

          if (setCount && p == tIdx) {
            writeln(order)
            OUT.flush()
            break
          }

          if (cnt == 0) {
            lastPIdx = p
            p = if (p + 1 < len) p + 1 else 0
            break
          }
        }

        p = if (p + 1 < len) p + 1 else 0
      }
    }
  }
}
