package 백준.Bronze.no1110

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 8
private const val OBS = 4
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
  var v = num
  var end = WS - 1
  do {
    WB[end--] = ((v % 10) + 48).toByte()
    v /= 10
  } while (v > 0)
  val stt = end + 1
  O.write(WB, stt, -stt + WS)
}


fun main() {
  val ch = IntArray(2)
  val cnts = IntArray(100).also { it[0] = 1 }
  val cycle = intArrayOf(3, 4, 12, 20, 60)
  intArrayOf(5, 26, 13, 2, 1).apply {
    for (i in 0 until this.size) {
      val v = this[i]
      val o1 = v / 10
      val o2 = v % 10
      ch[0] = o1
      ch[1] = o2
      while (true) {
        val a = ch[0]
        val b = ch[1]
        cnts[a * 10 + b] = cycle[i]
        ch[0] = b
        ch[1] = (a + b) % 10
        if (ch[0] == o1 && ch[1] == o2) break
      }
    }
  }

  w(cnts[i()])
  O.flush()
}
