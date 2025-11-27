package 백준.Silver.no1697

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 14
private const val OBS = 1 shl 4
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

private const val WS = 6
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

data class Pos(
  var v: Int,
  var t: Int,
)

fun main() {
  val n = i()
  val k = i()
  w(
    when {
    n >= k -> n - k

    else -> {
      var min = k - n
      val timer = IntArray(k * 2 + 1) { min }
      val q = ArrayList<Pos>()
      val p = Pos(k, 0)
      q.add(p)

      while (q.isNotEmpty()) {
        val pos = q.removeAt(0)
        val t = pos.t
        val v = pos.v
        if (v < 0 || t >= timer[v]) continue
        timer[v] = t
        if (v == n && t < min) {
          min = t
          continue
        }

        if (v % 2 == 0 && v >= n) q += Pos(v / 2, t + 1)
        q += Pos(v + 1, t + 1)
        q += Pos(v - 1, t + 1)
      }
      min
    }
  })
  O.flush()
}
