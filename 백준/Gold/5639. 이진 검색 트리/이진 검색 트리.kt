import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 100_000
private const val OBS = 100_000
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

private const val WS = 10
private val WB = ByteArray(WS + 1).also { it[WS] = 10 }
private fun w(
  num: Int,
) {
  var v = num
  var pos = WS - 1
  do {
    WB[pos--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  O.write(WB, ++pos, WS - pos + 1)
}

private val NUM = 48..57
private const val MAX = 10_000

fun main() {

  var v = 0
  var b: Byte
  val a = IntArray(MAX)
  var len = 0
  while (r().also { b = it } >= 10 || b == EOF.toByte()) {
    when (b) {
      EOF.toByte() -> break
      in NUM -> v = v * 10 + b - 48

      else -> a[len++] = v.also { v = 0 }
    }
  }

  fun postOrder(
    i: Int,
    max: Int,
  ) {
    val node = a[i]
    val l = i + 1

    var r = l
    while (r <= max) {
      if (a[r] > node) break
      r++
    }

    if (l < len && a[l] < node) postOrder(l, r - 1)
    if (r <= max) postOrder(r, max)

    w(node)
  }

  postOrder(0, len - 1)
  O.flush()
}