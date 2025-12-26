import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 2_000
private const val OBS = 100
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = BufferedInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0
private const val EOF = -1

private fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

private val NUM = 48..57
private fun i(): Int {
  var v = 0
  var s = 1
  var c: Byte
  while (r().also { c = it } in NUM || c == 45.toByte()) {
    when (c) {
      in NUM -> v = v * 10 + c - 48
      else -> s = -1
    }
  }
  return s * v
}

private const val WS = 10
private val WB = ByteArray(WS)
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
  pos++
  O.write(WB, pos, WS - pos)
}

fun main() {

  var N = i()
  val W = i()
  val ivs = IntArray(N)
  val iws = IntArray(N)
  var i = 0
  repeat(N) {
    val w = i()
    val v = i()
    if (v == 0 || w > W) {
      N--
      return@repeat
    }
    ivs[i] = v
    iws[i] = w
    i++
  }

  val dp = Array(N + 1) { IntArray(W + 1) }
  for (o in 1..N) {
    val i = o - 1
    val iv = ivs[i]
    val iw = iws[i]
    val prv = dp[o - 1]
    val cur = dp[o]
    repeat(W) {
      val w = it + 1
      val pw = prv[w]
      cur[w] = if (w >= iw) {
        val nw = iv + prv[w - iw]
        if (nw > pw) nw else pw
      } else pw
    }
  }

  w(dp[N][W])
  O.flush()
}