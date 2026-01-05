import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 60_000
private const val OBS = 1 shl 5
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
  repeat(n) {
    a[it] = i()
  }

  val k = i()

  fun swap(
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun partition(
    l: Int,
    r: Int,
  ): Int {
    val piv = (l + r) shr 1
    val mv = a[piv]
    swap(piv, r)

    var pos = l - 1
    repeat(r - l) {
      val j = l + it
      if (a[j] < mv) swap(++pos, j)
    }

    swap(++pos, r)
    return pos
  }

  fun qs(
    l: Int,
    r: Int,
  ) {
    if (l >= r) return
    val m = partition(l, r)
    qs(l, m - 1)
    qs(m + 1, r)
  }

  qs(0, n - 1)

  var l = 0
  var cnt = 0
  repeat(n) {
    val r = n - 1 - it
    val rv = a[r]
    if (rv >= k) return@repeat

    while (l < r) {
      val lv = a[l]
      val sum = rv + lv
      when {
        sum < k -> l++

        else -> {
          if (sum == k) cnt++
          break
        }
      }
    }
  }

  w(cnt)
  O.flush()
}