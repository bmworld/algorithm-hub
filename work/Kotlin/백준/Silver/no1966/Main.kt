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
  val numSize = 9
  val CNT = IntArray(numSize)
  repeat(readInt()) {
    repeat(numSize) { CNT[it] = 0 }
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

    var largeCnt = 0
    var p = 9 // 한 단계 상위
    for (v in min..max) {
      val cnt = CNT[v - 1]
      if (cnt == 0) continue
      if (v > t) largeCnt += cnt
      if (v in (t + 1) until p) p = v
    }

    var pIdx = -1
    var ntIdx = 0
    for (i in 0 until len) {
      val v = a[i]
      if (v == p) pIdx = i
      if (v == t) a[ntIdx++] = i
    }

    var passedT = false
    var tOrder = 0
    val tCnt = CNT[t - 1]
    for (i in 0 until tCnt) {
      val ntIdx = a[i]
      if (tIdx == ntIdx) {
        passedT = true
        tOrder++
      } else if (tIdx < pIdx) {
        if (!passedT || ntIdx > pIdx) tOrder++
      } else {
        if (ntIdx > pIdx) tOrder++
        if (passedT) break
      }
    }
    writeln(largeCnt + tOrder)
  }
  OUT.flush()
}
