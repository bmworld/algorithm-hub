import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 15
private const val OBS = 1 shl 3
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

private const val WS = 3
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

private const val DIRECT = 1
fun main() {
  val n = i()
  val dist = Array(n + 1) { IntArray(n + 1) { 100 } }
  val g = Array(n + 1) { mutableSetOf<Int>() }
  repeat(n) {
    dist[it + 1][it + 1] = 0
  }

  repeat(i()) {
    val a = i()
    val b = i()
    g[a] += b
    g[b] += a
    dist[a][b] = DIRECT
    dist[b][a] = DIRECT
  }

  for (mid in 1..n) {
    for (from in 1..n) {
      if (from == mid) continue
      for (to in from + 1..n) {
        val cur = dist[from][to]
        val acc = dist[from][mid] + dist[mid][to]
        if (acc >= cur) continue
        dist[from][to] = acc
        dist[to][from] = acc
      }
    }
  }

  var min = Int.MAX_VALUE
  var best = 1
  for (user in 1..n) {
    var num = 0
    for (frnd in 1..n) num += dist[user][frnd]
    if (num >= min) continue
    min = num
    best = user
  }

  w(best)
  O.flush()
}