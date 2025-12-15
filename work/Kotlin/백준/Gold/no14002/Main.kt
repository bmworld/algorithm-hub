package 백준.Gold.no14002

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

fun main() {
  val end = i()
  val a = IntArray(end)
  val order = IntArray(end)
  var maxOrder = 0


  fun getOrder(
    v: Int,
    end: Int,
  ): Int {
    var max = 0
    for (i in 0 until end) {
      val o = order[i]
      if (v > a[i] && o > max) max = o
    }
    return max + 1
  }

  repeat(end) {
    val v = i()
    a[it] = v
    val o = getOrder(v, it)
    order[it] = o
    if (o > maxOrder) maxOrder = o
  }

  w(maxOrder, true)

  val lis = IntArray(maxOrder)
  for (i in end - 1 downTo 0) {
    if (order[i] != maxOrder) continue
    lis[maxOrder - 1] = a[i]
    maxOrder--
    if (maxOrder == 0) break
  }
  for (v in lis) w(v, false)

  O.flush()
}
