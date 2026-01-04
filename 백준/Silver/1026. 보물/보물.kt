import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 512
private const val OBS = 512
private val O = BufferedOutputStream(System.out, OBS)
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
  O.write(WB, ++pos, WS - pos)
}

fun main() {
  val n = i()
  val a = IntArray(n)
  val b = IntArray(n)
  repeat(n) {
    a[it] = i()
  }
  repeat(n) {
    b[it] = i()
  }

  qs(a, 0, n - 1)
  qs(b, 0, n - 1)

  var r = 0
  repeat(n) {
    r += a[it] * b[n - 1 - it]
  }

  w(r)
  O.flush()
}

fun swap(
  a: IntArray,
  i: Int,
  j: Int,
) {
  val tmp = a[i]
  a[i] = a[j]
  a[j] = tmp
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
        swap(a, pos, pl)
        pl++
        pos++
      }

      v > piv -> {
        swap(a, pos, pr)
        pr--
      }

      else -> pos++
    }
  }
  return Pair(pl, pr)
}

fun qs(
  a: IntArray,
  l: Int,
  r: Int,
) {
  if (l >= r) return
  val (pl, pr) = `3way_qs`(a, l, r)
  qs(a, l, pl - 1)
  qs(a, pr + 1, r)
}