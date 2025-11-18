package 백준.Silver.no11659

import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 20)

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

private const val MAX_NUM_LEN = 15
private val outBuf = ByteArray(MAX_NUM_LEN + 1).also { it[MAX_NUM_LEN] = '\n'.code.toByte() }

private fun w(num: Long) {
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

private const val SEP = 1_000_000
fun main() {
  val N = i()
  val M = i()
  val map = LinkedHashMap<Int, Long>(M)
  val a = IntArray(N)
  var total = 0
  repeat(N) {
    val v = i()
    a[it] = v
    total += v
  }

  repeat(M) {
    val i = i() - 1
    val j = i() - 1
    map[it] = (i * SEP + j).toLong()
  }

  var l = 0
  var r = 0
  var sum = a[0]
  for (k in map.entries.sortedWith(Comparator.comparing { it.value })) {
    val i = (k.value / SEP).toInt()
    val j = (k.value % SEP).toInt()

    map[k.key] = when (j - i) {
      in 1..N - 2 -> {
        while (l != i) if (l < i) sum -= a[l++] else sum += a[--l]
        while (r != j) if (r < j) sum += a[++r] else sum -= a[r--]
        sum
      }

      0 -> a[i]
      else -> total
    }.toLong()
  }
  for (k in map) w(k.value)
  OUT.flush()
}
