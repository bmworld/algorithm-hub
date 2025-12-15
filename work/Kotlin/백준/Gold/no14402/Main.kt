package 백준.Gold.no14402

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 6_000
private const val OBS = 1_000
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
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

private fun op(): Byte {
  var c: Byte
  while (r().also { c = it } <= 32) {
  }
  r()
  return c
}

private const val WS = 10
private val WB = ByteArray(WS + 1)
private fun w(
  num: Int,
  nl: Boolean,
) {
  var v = if (num >= 0) num
  else {
    O.write(45)
    -num
  }
  WB[WS] = (if (nl) '\n' else ' ').code.toByte()
  var pos = WS - 1
  do {
    WB[pos--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  pos++
  O.write(WB, pos, WS - pos + 1)
}

private const val MAX = 1000
fun main() {
  val a = BooleanArray(MAX + 1)
  var len = 0
  var min = MAX
  var max = 1
  repeat(i()) {
    val v = i()
    if (!a[v]) {
      len++
      a[v] = true
    }
    if (v < min) min = v
    if (v > max) max = v
  }

  w(len, true)
  for (i in min..max) if (a[i]) w(i, false)
  O.flush()
}
