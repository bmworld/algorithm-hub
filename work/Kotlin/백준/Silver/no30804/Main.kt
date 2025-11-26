package 백준.Silver.no30804

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 15
private const val OBS = 1 shl 3
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
private val IB = ByteArray(IBS)
private const val EOF = -1
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
  var kind = 0
  val a = IntArray(n)
  val c = IntArray(MaxNum + 1)
  repeat(n) {
    val v = i()
    a[it] = v
    if (c[v]++ == 0) kind++
  }

  var l = 0
  var r = n - 1
  while (l < r && kind > 2) {
    val lv = a[l]
    val rv = a[r]
    val lc = c[lv]
    val rc = c[rv]
    if (lv == rv) {
      c[lv] -= 2
      if (c[lv] == 0) kind--
      l++
      r--
      continue
    }

    if (lc >= rc) {
      if (--c[rv] == 0) kind--
      r--
    } else {
      if (--c[lv] == 0) kind--
      l++
    }
  }

  var cnt = 0
  repeat(MaxNum) {
    cnt += c[it + 1]
  }
  w(cnt)
  O.flush()
}
