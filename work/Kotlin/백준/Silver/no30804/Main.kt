package 백준.Silver.no30804

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 13
private const val OBS = 1 shl 3
private const val EOF = -1
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0

private fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

private fun i(): Int {
  var v = 0
  var c: Byte
  while (r().also { c = it } in 48..57) v = v * 10 + (c - 48)
  return v
}


private const val WS = 10
private val WB = ByteArray(WS)

private fun w(
  num: Int,
) {
  var x = num
  var end = WS - 1
  do {
    WB[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = end + 1
  O.write(WB, stt, WS - stt)
}

private const val MaxNum = 9
fun main() {
  val n = i()
  val a = IntArray(n)
  repeat(n) {
    a[it] = i()
  }

  val cnt = IntArray(MaxNum + 1)
  var max = 1
  var knd = 0
  var l = 0
  var r = 0
  while (r < n) {
    if (cnt[a[r]]++ == 0) knd++
    while (knd > 2) if (--cnt[a[l++]] == 0) knd--
    val len = r - l + 1
    if (len > max) max = len
    r++
  }

  w(max)
  O.flush()
}
