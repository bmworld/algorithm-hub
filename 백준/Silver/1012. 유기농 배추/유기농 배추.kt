import java.io.BufferedOutputStream

private val OUT = BufferedOutputStream(System.`out`, 1 shl 12)

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
private val outBuf = ByteArray(MAX_NUM_LEN + 1).also { it[MAX_NUM_LEN] = '\n'.code.toByte() }

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
    MAX_NUM_LEN - stt + 1, // + 개행
  )
}

private const val O = 1.toByte()
private const val X = 0.toByte()
private const val XY_SEP = 100
private val dx = IntArray(4).also {
  it[0] = 0 // 상
  it[1] = 0 // 하
  it[2] = -1 // 좌
  it[3] = 1 // 우
}

private val dy = IntArray(4).also {
  it[0] = -1
  it[1] = 1
  it[2] = 0
  it[3] = 0
}

fun main() {

  var t = i()
  while (t-- > 0) {
    val m = i()
    val n = i()
    val k = i()
    val a = Array(m) { ByteArray(n) }
    val q = IntArray(k)
    var qi = 0
    repeat(k) {
      val x = i()
      val y = i()
      a[x][y] = O
      q[qi++] = x * XY_SEP + y
    }

    if (k == 1 || k >= 2499) {
      w(1)
      continue
    }

    var rem = k
    fun bfs(
      x: Int,
      y: Int,
    ) {
      if (a[x][y] == X || rem == 0) return
      a[x][y] = X
      rem--
      for (i in 0..3) {
        val nx = x + dx[i]
        val ny = y + dy[i]
        if (nx in 0 until m && ny in 0 until n && a[nx][ny] == O) bfs(nx, ny)
      }
    }

    var wormCnt = 0
    for (i in 0 until k) {
      val v = q[i]
      val x = v / XY_SEP
      val y = v % XY_SEP
      if (a[x][y] == X) continue
      wormCnt++
      bfs(x, y)
    }
    w(wormCnt)
  }
  OUT.flush()
}
