package 백준.Silver.no17087

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.util.*
import kotlin.math.sqrt

private const val IBS = 60_000
private const val OBS = 1 shl 5
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
  pos++
  O.write(WB, pos, WS - pos)
}

private const val MAX = 1_000_000_000
fun main() {
  val n = i()
  val k = i()

  val a = IntArray(n)
  var minDiff = MAX
  repeat(n) {
    val v = i()
    val subt = k - v
    val diff = if (subt < 0) -subt else subt
    a[it] = diff
    if (diff < minDiff) minDiff = diff
  }

  var gcd = 1
  val pq = getCd(minDiff)
  while (pq.isNotEmpty()) {
    val v = pq.poll()
    var isGcd = true
    for (i in 0 until n - 1) {
      if (a[i] % v != 0) {
        isGcd = false
        break
      }
    }
    if (!isGcd) continue
    else if (v > gcd) gcd = v
    else break
  }

  w(gcd)
  O.flush()
}

private fun getCd(v: Int): PriorityQueue<Int> {
  val pq = PriorityQueue<Int>(Comparator.reverseOrder())
  pq.add(1)
  pq.add(v)

  val sqrt = sqrt(v.toDouble()).toInt()
  for (i in sqrt downTo 2) {
    if (v % i == 0) {
      pq.add(v / i)
      pq.add(i)
    }
  }
  return pq
}
