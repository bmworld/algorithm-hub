package 백준.Silver.no4673

import java.io.BufferedOutputStream

private const val OBS = 10_000
private val O = BufferedOutputStream(System.`out`, OBS)

private const val WS = 10
private val WB = ByteArray(WS + 1).also { it[WS] = 10 }
private fun w(
  num: Int,
) {
  var v = if (num >= 0) num
  else {
    O.write(45)
    -num
  }
  var pos = WS - 1
  do {
    WB[pos--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  O.write(WB, ++pos, WS - pos + 1)
}

private const val MAX = 10_000
fun main() {

  val ch = BooleanArray(MAX + 1)
  fun builder(
    v: Int,
  ) {
    var nv = v
    var x = v
    do {
      nv += x % 10
      x /= 10
    } while (x > 0)

    if (nv > MAX || ch[nv]) return
    ch[nv] = true
    builder(nv)
  }

  repeat(MAX) {
    builder(it + 1)
  }

  repeat(MAX) {
    val n = it + 1
    if (!ch[n]) w(n)
  }
  O.flush()
}
