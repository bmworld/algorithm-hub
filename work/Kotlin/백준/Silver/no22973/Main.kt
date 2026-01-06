package 백준.Silver.no22973

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 4
private const val OBS = 1 shl 2
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
private fun i(): Long {
  var v = 0L
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

fun main() {
  var k = i()
  w(
    when {
      k == 0L -> 0
      k % 2 == 0L -> -1

      else -> {
        if (k < 0) k = -k
        var cnt = 0
        var jump = 0L
        while (true) {
          val next = if (jump == 0L) 1L else jump * 2
          if (next > k) break
          jump = next
          cnt++
        }
        cnt
      }
    }
  )
  O.flush()
}
