package 백준.Silver.no3273

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 20_000
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

private const val MAX_NUM = 1_000_000
fun main() {
  val ch = BooleanArray(MAX_NUM + 1)
  val n = i()
  var max = 0
  repeat(n) {
    val v = i()
    ch[v] = true
    if (max < v) max = v
  }
  val k = i()

  var cnt = 0
  var l = 1
  var r = if (max < k) max else k
  while (l < r) {
    when {
      !ch[l] -> {
        l++
        continue
      }

      !ch[r] -> {
        r--
        continue
      }
    }

    val sum = l + r
    when {
      sum < k -> l++

      else -> {
        if (sum == k) cnt++
        r--
      }
    }
  }

  w(cnt)
  O.flush()
}
