import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 19
private const val OBS = 1 shl 4
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
private val WB = ByteArray(WS)
private fun w(
  num: Int,
) {
  var v = num
  var pos = WS - 1
  do {
    WB[pos--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  O.write(WB, ++pos, WS - pos)
}

fun main() {
  var min = 0
  val N = i()
  val S = i()

  val a = IntArray(N)
  var sum = 0
  var l = 0
  repeat(N) { r ->
    val v = i()
    a[r] = v
    sum += v

    while (sum >= S) {
      val seq = r - l + 1
      if (min == 0 || min > seq) min = seq
      sum -= a[l++]
    }
  }

  w(min)
  O.flush()
}