import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 10
private const val OBS = 1 shl 10
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
private val WB = ByteArray(WS + 1).also { it[WS] = 32 }
private fun w(
  num: Int
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

private const val SEP = 100
fun main() {
  val N = i()
  val b = IntArray(N) { i() * SEP + it }

  fun swap(
    i: Int,
    j: Int,
  ) {
    val tmp = b[i]
    b[i] = b[j]
    b[j] = tmp
  }

  fun `3way_qs`(
    a: IntArray,
    l: Int,
    r: Int,
  ): Pair<Int, Int> {
    var pos = l
    var pl = l
    var pr = r
    val piv = a[(l + r) shr 1]

    while (pos <= pr) {
      val v = a[pos]
      when {
        v < piv -> {
          swap(pos, pl)
          pl++
          pos++
        }

        v > piv -> {
          swap(pos, pr)
          pr--
        }

        else -> pos++
      }
    }
    return Pair(pl, pr)
  }

  fun qs(
    l: Int,
    r: Int,
  ) {
    if (l >= r) return
    val (pl, pr) = `3way_qs`(b, l, r)
    qs(l, pl - 1)
    qs(pr + 1, r)
  }

  qs(0, N - 1)

  val p = IntArray(N)
  repeat(N) { bi ->
    val ai = b[bi] % SEP
    p[ai] = bi
  }

  repeat(N) {
    w(p[it])
  }

  O.flush()
}