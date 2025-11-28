import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 17
private const val OBS = 1 shl 17
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

private const val WS = 10
private val WB = ByteArray(WS + 1)

private fun w(
  num: Int,
  nl: Boolean,
) {
  var x = num
  if (x < 0) {
    O.write('-'.code)
    x = -x
  }
  WB[WS] = (if (nl) '\n' else ' ').code.toByte()
  var end = WS - 1
  do {
    WB[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = end + 1
  O.write(WB, stt, WS - stt + 1)
}

private val dx = intArrayOf(-1, 0, 1, 0)
private val dy = intArrayOf(0, -1, 0, 1)
private const val SEP = 10000

fun main() {
  val n = i()
  val m = i()
  val a = Array(n) { IntArray(m) }
  var stt = 0
  repeat(n) { i ->
    repeat(m) { j ->
      val v = i()
      a[i][j] = v
      if (v == 2) stt = i * SEP + j
    }
  }

  val q = IntArray(n * m)
  val ch = Array(n) { BooleanArray(m) }
  var l = 0
  var r = 0
  q[r++] = stt
  a[stt / SEP][stt % SEP] = 0
  ch[stt / SEP][stt % SEP] = true

  while (l < r) {
    val v = q[l++]
    val i = v / SEP
    val j = v % SEP
    val cur = a[i][j]
    repeat(4) { k ->
      val ni = i + dx[k]
      val nj = j + dy[k]
      if (ni in 0 until n && nj in 0 until m && a[ni][nj] == 1 && !ch[ni][nj]) {
        a[ni][nj] = cur + 1
        ch[ni][nj] = true
        q[r++] = ni * SEP + nj
      }
    }
  }

  repeat(n) { i ->
    repeat(m) { j ->
      val v = a[i][j]
      w(if (v == 1 && !ch[i][j]) -1 else v, j >= m - 1)
    }
  }
  O.flush()
}