import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 14
private const val OBS = 1 shl 2
private const val EOF = -1
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0

private fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

private fun i(): Int {
  var v = 0
  var c: Byte
  while (r().also { c = it } in 48..57) v = v * 10 + (c - 48)
  return v
}


private fun b(): Byte {
  var c = r()
  while (c !in 48..49) c = r()
  return if (c == 48.toByte()) N else Y
}

private const val WS = 10
private val WB = ByteArray(WS)

private fun w(
  num: Int,
) {
  var x = num
  var end = WS - 1
  do {
    WB[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = end + 1
  O.write(WB, stt, WS - stt)
}

private const val Y = 1.toByte()
private const val N = 0.toByte()
private const val SEP = 1000
private val dx = intArrayOf(0, 0, -1, 1)
private val dy = intArrayOf(-1, 1, 0, 0)
fun main() {
  val n = i()
  val m = i()
  val a = Array(n) { ByteArray(m) }
  val cnt = Array(n) { IntArray(m) }
  repeat(n) { i ->
    repeat(m) { j ->
      a[i][j] = b()
    }
  }

  val q = IntArray(n * m + 2)
  var head = 0
  var tail = 0
  q[tail++] = 0
  a[0][0] = N
  cnt[0][0] = 1

  while (head < tail) {
    val v = q[head++]
    val i = v / SEP
    val j = v % SEP
    val pCnt = cnt[i][j]
    for (k in 0..3) {
      val ni = i + dx[k]
      val nj = j + dy[k]
      if (ni !in 0 until n || nj !in 0 until m || a[ni][nj] == N || cnt[ni][nj] > 0) continue
      a[ni][nj] = N
      cnt[ni][nj] = pCnt + 1
      q[tail++] = ni * SEP + nj
    }
  }

  w(cnt[n - 1][m - 1])
  O.flush()
}
