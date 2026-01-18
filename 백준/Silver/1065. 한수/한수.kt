import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 3
private const val OBS = 1 shl 3
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
  flush: Boolean = true,
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
  O.write(WB, ++pos, WS - pos)
  if (flush) O.flush()
}

private const val MAX_LEN = 10
private const val DIGIT_CNT = 10
private const val CNT_UNTIL_2ND_PLACE = 99
private val DIFF_RANGE = intArrayOf(-4, -3, -2, -1, 0, 1, 2, 3, 4)

fun main() {
  val N = i()
  if (N <= CNT_UNTIL_2ND_PLACE) {
    w(N)
    return
  }

  var len = 0
  val NUM = IntArray(MAX_LEN).also {
    var x = N
    while (x > 0) {
      it[len++] = x % 10
      x /= 10
    }
  }

  var cnt = CNT_UNTIL_2ND_PLACE
  repeat(DIFF_RANGE.size) {
    val delta = DIFF_RANGE[it]
    repeat(DIGIT_CNT) { firstDgt ->
      var di = 0
      NUM[di++] = firstDgt
      var prv = firstDgt
      for (i in di until len) {
        val dgt = prv + delta
        if (dgt !in 1..9) break
        NUM[i] = dgt
        if (i == len - 1 && toNumber(len, NUM) > N) continue
        if (i >= 2) cnt++
        prv = dgt
      }
    }
  }
  w(cnt)
}

private fun toNumber(
  lastIdx: Int,
  NUM: IntArray
): Int {
  var v = 0
  var i = lastIdx
  while (i >= 0) v = v * 10 + NUM[i--]
  return v
}