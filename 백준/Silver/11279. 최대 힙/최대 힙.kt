import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 15
private const val OBS = 1 shl 9
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
private val IB = ByteArray(IBS)
private const val EOF = -1
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
  var n = 0
  var c: Byte
  while (r().also { c = it } in 48..57) n = n * 10 + (c - 48)
  return n
}

private const val WS = 10
private val WB = ByteArray(WS + 1).also { it[WS] = '\n'.code.toByte() }

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

  O.write(WB, stt, WS - stt + 1)
}

fun main() {
  val n = i()
  val heap = IntArray(n + 1)
  var len = 0
  val root = 1

  fun insert(v: Int) {
    var ci = ++len
    heap[len] = v
    while (ci > root) {
      val pi = ci shr 1
      val p = heap[pi]
      val c = heap[ci]
      if (p < c) {
        heap[pi] = c
        heap[ci] = p
        ci = pi
      } else break
    }

  }

  fun pop(): Int {
    if (len == 0) return 0

    val v = heap[root]
    heap[root] = heap[len]
    heap[len] = 0
    len--

    var pi = root
    while (true) {
      val li = pi shl 1
      val end = li > len
      if (end) break
      val ri = li + 1
      var maxIdx = li
      if (ri <= len && heap[ri] > heap[li]) maxIdx = ri
      val p = heap[pi]
      val max = heap[maxIdx]
      if (p < max) {
        heap[pi] = max
        heap[maxIdx] = p
        pi = maxIdx
      } else break
    }
    return v
  }

  repeat(n) {
    val x = i()
    when {
      x == 0 -> w(pop())
      else -> insert(x)
    }
  }
  O.flush()
}