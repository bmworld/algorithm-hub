package 백준.Bronze.no1157

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 11
private const val OBS = 1 shl 2
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

private const val A = 65
private const val a = 97
private const val LEN = 26

fun main() {
  val ch = IntArray(LEN)
  var fi = 0
  var si = 0
  var maxCnt = 0
  var v: Int
  while (r().also {
      val i = it.toInt()
      v = if (i >= a) i - a else i - A
    } >= A) {
    val cnt = ++ch[v]
    if (cnt > maxCnt) {
      maxCnt = cnt
      fi = v
      si = v
    } else if (cnt == maxCnt) si = v
  }

  O.write(
    when {
      fi != si -> 63
      else -> fi + A
    }
  )
  O.flush()
}
