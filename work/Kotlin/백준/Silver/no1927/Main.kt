package 백준.Silver.no1927

import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.util.*

private const val IBS = 1 shl 16
private const val OBS = 1 shl 11
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
  var n = 0
  var c: Byte
  while (r().also { c = it } in 48..57) n = n * 10 + (c - 48)
  return n
}

private const val WS = 10
private val WB = ByteArray(WS + 1).also { it[WS] = '\n'.code.toByte() }

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

  O.write(WB, stt, WS - stt + 1)
}

fun main() {
  val h = i()
  val q = PriorityQueue<Int>()
  repeat(h) {
    val x = i()
    when {
      x == 0 -> {
        if (q.isEmpty()) w(0)
        else w(q.poll())
      }

      else -> q.offer(x)
    }
  }
  O.flush()
}
