package 백준.Silver.no11724

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 11
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
private val WB = ByteArray(WS + 1).also { it[WS] = '\n'.code.toByte() }

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

  O.write(WB, stt, WS - stt + 1)
}


fun main() {

  val n = i()
  val g = Array(n + 1) { mutableSetOf<Int>() }
  val ch = BooleanArray(n + 1)
  repeat(i()) {
    val a = i()
    val b = i()
    g[a].add(b)
    g[b].add(a)
  }

  fun dfs(v: Int) {
    if (ch[v]) return
    ch[v] = true
    for (nv in g[v]) dfs(nv)
  }

  var cnt = 0
  for (v in 1..n) {
    if (ch[v]) continue
    cnt++
    dfs(v)
  }

  w(cnt)
  O.flush()
}
