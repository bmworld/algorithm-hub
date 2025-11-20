package 백준.Silver.no1260

import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 19)

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

private const val MAX_NUM_LEN = 4
private val outBuf = ByteArray(MAX_NUM_LEN + 1).also { it[MAX_NUM_LEN] = ' '.code.toByte() }

private fun w(
  num: Int,
) {
  var x = num
  var end = MAX_NUM_LEN - 1
  do {
    outBuf[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = end + 1

  OUT.write(
    outBuf,
    stt,
    MAX_NUM_LEN - stt + 1, // + 띄
  )
}

private const val O = 1.toByte()
private const val X = 0.toByte()

fun main() {
  val n = i()
  val m = i()
  val v = i()
  val arr = Array<MutableList<Int>>(n + 1) { mutableListOf() }
  repeat(m) {
    val a = i()
    val b = i()
    arr[a].add(b)
    arr[b].add(a)
  }

  for (list in arr) list.sort()

  val dCh = ByteArray(1001)
  fun dfs(v: Int) {
    dCh[v] = O
    w(v)
    for (nv in arr[v]) if (dCh[nv] == X) dfs(nv)
  }
  dfs(v)

  OUT.write('\n'.code)

  fun bfs(v: Int) {
    val bCh = ByteArray(1001)
    val q = IntArray(1000)
    var head = 0
    var tail = 0
    bCh[v] = O
    q[tail++] = v

    while (head < tail) {
      val n = q[head++]
      w(n)
      for (nv in arr[n]) {
        if (bCh[nv] == X) {
          bCh[nv] = O
          q[tail++] = nv
        }
      }
    }
  }
  bfs(v)
  OUT.flush()
}
