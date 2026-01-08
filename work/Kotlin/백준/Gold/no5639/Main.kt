package 백준.Gold.no5639

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
private const val MAX_NODE = 1_000_000
private const val EMPTY = 0


fun main() {

  var v = 0
  var b: Byte
  var ROOT = EMPTY
  var prev = EMPTY
  val l = IntArray(MAX_NODE + 1)
  val r = IntArray(MAX_NODE + 1)
  while (r().also { b = it } >= 10 || b == EOF.toByte()) {
    when (b) {
      EOF.toByte() -> break
      in NUM -> v = v * 10 + b - 48

      else -> {
        when {
          ROOT == EMPTY -> ROOT = v
          prev > v -> l[prev] = v

          else -> {
            var root = ROOT
            while (true) {
              if (root > v) {
                val lv = l[root]
                if (lv == EMPTY) {
                  l[root] = v
                  break
                } else root = lv
              } else {
                val rv = r[root]
                if (rv == EMPTY) {
                  r[root] = v
                  break
                } else root = rv
              }
            }
          }
        }

        prev = v
        v = 0
      }
    }
  }

  fun postOrder(v: Int) {
    val lv = l[v]
    if (lv != EMPTY) postOrder(lv)
    val rv = r[v]
    if (rv != EMPTY) postOrder(rv)
    w(v)
  }

  postOrder(ROOT)
  O.flush()
}
