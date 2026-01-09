import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 20
private const val OBS = 1 shl 20
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
  var c: Byte
  while (r().also { c = it } in NUM) v = v * 10 + c - 48
  return v
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
  O.write(WB, ++pos, WS - pos + 1)
}

fun main() {

  val N = i()
  var K = i()
  val a = IntArray(N) { i() }
  val tmp = IntArray(N)

  fun merge(
    l: Int,
    m: Int,
    r: Int,
  ) {
    var i = l
    var j = m + 1
    var t = 0
    while (i <= m && j <= r) tmp[t++] = if (a[i] <= a[j]) a[i++] else a[j++]
    while (i <= m) tmp[t++] = a[i++]
    while (j <= r) tmp[t++] = a[j++]

    i = l
    t = 0
    while (K > 0 && i <= r) {
      a[i++] = tmp[t++]
      K--
    }
  }

  fun merge_sort(
    l: Int,
    r: Int,
  ) {
    if (K <= 0 || l >= r) return
    val m = (l + r) shr 1
    merge_sort(l, m)
    merge_sort(m + 1, r)
    merge(l, m, r)
  }
  merge_sort(0, N - 1)

  if (K > 0) w(-1)
  else {
    repeat(N) {
      w(a[it])
    }
  }

  O.flush()
}