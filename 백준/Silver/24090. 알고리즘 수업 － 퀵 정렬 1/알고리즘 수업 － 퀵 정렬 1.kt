import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1_000
private const val OBS = 10_000
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
  O.write(WB, pos, WS - pos + 1)
}

fun main() {
  val n = i()
  val k = i()
  val a = IntArray(n)
  repeat(n) {
    a[it] = i()
  }

  val tracer = IntArray(2) { -1 }
  var cnt = 0

  fun count(
    low: Int,
    high: Int,
  ) {
    if (++cnt != k) return
    tracer[0] = low
    tracer[1] = high
  }

  fun sorter(
    l: Int,
    r: Int,
  ): Int {
    val mv = a[r]

    var i = l - 1
    for (j in l until r) {
      val lo = a[j]
      if (lo > mv) continue
      val hi = a[++i]
      a[i] = lo
      a[j] = hi
      count(lo, hi)
    }

    val m = i + 1
    if (m != r) {
      val hi = a[m]
      a[r] = hi
      a[m] = mv
      count(mv, hi)
    }

    return m
  }

  fun qs(
    l: Int,
    r: Int,
  ) {
    if (l >= r) return
    
    val m = sorter(l, r)
    qs(l, m - 1)
    qs(m + 1, r)
  }

  qs(0, n - 1)

  var i = 0
  val v1 = tracer[i++]
  w(v1)
  if (v1 != -1) w(tracer[i])

  O.flush()
}