package 백준.Silver.no1747

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 4
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
  O.write(WB, ++pos, WS - pos)
}

private const val MAX_LEN = 7
private const val MAX_PRIME = 1_003_001
fun main() {
  val N = i()
  var min = N
  val max = MAX_PRIME
  val primes = BooleanArray(max + 1) { true }.also {
    it[0] = false
    it[1] = false
    var d = 2
    while (d * d <= max) {
      for (v in d * d..max step d) it[v] = false
      d++
    }
  }

  val NUM = IntArray(MAX_LEN)
  var r = Int.MAX_VALUE
  for (v in min..max) {
    if (primes[v] && isPalindrome(v, NUM)) {
      r = v
      break
    }
  }


  w(r)
  O.flush()
}

private fun isPalindrome(
  v: Int,
  NUM: IntArray
): Boolean {
  var len = 0
  var x = v
  while (x > 0) {
    NUM[len++] = x % 10
    x /= 10
  }

  val first = NUM[len - 1]
  if (len > 1 && (first % 2 == 0 || first % 5 == 0)) return false
  for (l in 0 until len / 2) if (NUM[l] != NUM[len - 1 - l]) return false
  return true
}
