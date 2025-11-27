package 백준.Silver.no1389

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
  val d = Array(n + 1) { IntArray(n + 1) { 100 } }
  repeat(n) {
    d[it + 1][it + 1] = 0
  }

  repeat(i()) {
    val a = i()
    val b = i()
    d[a][b] = DIRECT
    d[b][a] = DIRECT
  }

  repeat(n) { i ->
    val mid = i + 1
    repeat(n) { j ->
      val from = j + 1
      if (from != mid) {
        repeat(n - from) { k ->
          val to = from + k + 1
          val cur = d[from][to]
          val acc = d[from][mid] + d[mid][to]
          if (acc < cur) {
            d[from][to] = acc
            d[to][from] = acc
          }
        }
      }
    }
  }

  var min = Int.MAX_VALUE
  var best = 1
  repeat(n) { i ->
    val user = i + 1
    var num = 0
    repeat(n) { j ->
      val frnd = j + 1
      num += d[user][frnd]
    }
    if (num < min) {
      min = num
      best = user
    }
  }

  w(best)
  O.flush()
}
