import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 16
private const val OBS = 1 shl 2
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
private val IB = ByteArray(IBS)
private const val EOF = -1
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
  var n = 0
  var c: Byte
  while (r().also { c = it } in 48..57) n = n * 10 + (c - 48)
  return n
}

private const val WS = 4
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


fun main() {
  val n = i()
  val g = Array(n + 1) { ArrayList<Int>() }
  val ch = BooleanArray(n + 1)
  repeat(i()) {
    val a = i()
    val b = i()
    g[a] += b
    g[b] += a
  }

  fun dfs(v: Int) {
    if (ch[v]) return
    ch[v] = true
    val lnks = g[v]
    repeat(lnks.size) {
      dfs(lnks[it])
    }
  }

  var cnt = 0
  repeat(n) {
    val v = it + 1
    if (!ch[v]) {
      cnt++
      dfs(v)
    }
  }

  w(cnt)
  O.flush()
}