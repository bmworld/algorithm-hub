package 백준.Silver.no1389

import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.util.*

private const val IBS = 1 shl 13
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

data class User(
  val n: Int,
  val dist: Int,
)

fun main() {
  val n = i()
  val frnd = Array(n + 1) { mutableListOf<Int>() }
  val dist = IntArray(n + 1)
  repeat(i()) {
    val a = i()
    val b = i()
    frnd[a] += b
    frnd[b] += a
  }

  fun bfs(
    from: Int,
    to: Int,
  ) {
    val ch = BooleanArray(n + 1)
    val q = LinkedList<User>()
    q.add(User(from, 0))
    ch[from] = true

    while (q.isNotEmpty()) {
      val u = q.poll()
      for (f in frnd[u.n]) {
        val nd = u.dist + 1
        when {
          f == to -> {
            dist[from] += nd
            dist[to] += nd
            return
          }

          ch[f] -> continue

          else -> {
            ch[f] = true
            q.add(User(f, nd))
          }
        }
      }
    }
  }

  var minD = Int.MAX_VALUE
  var bestUser = 0
  for (u in 1..n) {
    for (f in u + 1..n) bfs(u, f)
    val d = dist[u]
    if (d >= minD) continue
    minD = d
    bestUser = u
  }

  w(bestUser)
  O.flush()
}
