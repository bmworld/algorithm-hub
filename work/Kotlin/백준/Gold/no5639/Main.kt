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

fun main() {

  var v = 0
  var b: Byte
  var ROOT: Node? = null
  var prev: Node? = null
  while (r().also { b = it } >= 10 || b == EOF.toByte()) {
    when (b) {
      EOF.toByte() -> break
      in NUM -> v = v * 10 + b - 48

      else -> {
        val node = Node(v)
        when {
          ROOT == null -> ROOT = node
          prev!!.v > v -> prev.l = node

          else -> {
            var root = ROOT!!
            while (true) {
              if (root.v > v) {
                val l = root.l
                if (l == null) {
                  root.l = node
                  break
                } else root = l
              } else {
                val r = root.r
                if (r == null) {
                  root.r = node
                  break
                } else root = r
              }
            }
          }
        }

        prev = node
        v = 0
        continue
      }
    }
  }

  fun postOrder(node: Node) {
    val l = node.l
    if (l != null) postOrder(l)
    val r = node.r
    if (r != null) postOrder(r)
    w(node.v)
  }

  postOrder(ROOT!!)
  O.flush()
}

private data class Node(
  val v: Int,
  var l: Node? = null,
  var r: Node? = null,
)
