package 백준.Silver.no1260

import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 15)

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

fun main() {
  val n = i()
  val m = i()
  val v = i()
  val map = LinkedHashMap<Int, MutableList<Int>>()
  repeat(m) {
    val a = i()
    val b = i()
    if (map[a] == null) map[a] = mutableListOf()
    map[a]!!.add(b)
    if (map[b] == null) map[b] = mutableListOf()
    map[b]!!.add(a)
  }

  for (list in map) list.value.sort()

  val dCh = ByteArray(1000)
  fun dfs(v: Int) {
    if (dCh[v - 1] == O) return
    dCh[v - 1] = O
    w(v)
    for (nv in map[v]!!) dfs(nv)
  }
  dfs(v)

  OUT.write('\n'.code)

  fun bfs(v: Int) {
    val bCh = ByteArray(1000)
    val q = IntArray(1000)
    var head = 0
    var tail = 0
    bCh[v - 1] = O
    q[tail++] = v

    while (head < tail) {
      val n = q[head++]
      w(n)
      for (nv in map[n]!!) {
        if (bCh[nv - 1] == O) continue
        bCh[nv - 1] = O
        q[tail++] = nv
      }
    }
  }
  bfs(v)
  OUT.flush()
}
