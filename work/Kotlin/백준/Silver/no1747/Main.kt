package 백준.Silver.no1747

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 5
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
fun main() {
  val N = i()
  val NUM = IntArray(MAX_LEN)
  var found = false

  fun dfs(
    l: Int,
    len: Int,
  ) {
    if (found) return
    if (l > len / 2) {
      val v = toNum(NUM, len)
      if (v >= N && isPrime(v)) {
        w(v)
        found = true
      }
      return
    }

    val r = len - 1 - l
    for (v in 0..9) {
      if (l == 0 && v == 0
        || len >= 2 && l == 0 && (v % 2 == 0 || v % 5 == 0)
      ) continue
      NUM[l] = v
      NUM[r] = v
      dfs(l + 1, len)
    }
  }

  for (len in getLen(N)..MAX_LEN) dfs(0, len)
  O.flush()
}

private fun toNum(
  NUM: IntArray,
  len: Int
): Int {
  var v = 0
  repeat(len) {
    v = v * 10 + NUM[it]
  }
  return v
}

private fun isPrime(v: Int): Boolean {
  if (v == 1) return false
  var d = 2
  while (d * d <= v) {
    if (v % d == 0) return false
    d++
  }
  return true
}

private fun getLen(
  v: Int,
): Int {
  var len = 0
  var x = v
  while (x > 0) {
    x /= 10
    len++
  }
  return len
}
