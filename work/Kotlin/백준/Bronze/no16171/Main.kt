package 백준.Bronze.no16171

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 100
private const val OBS = 1
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

private const val MAX_LEN = 200_000
private val sb = StringBuilder(MAX_LEN)
private fun s(): String {
  var c = r()
  while (c != EOF.toByte() && c <= 32) c = r()
  while (c >= 48) {
    if (c in 65..90 || c in 97..122) sb.append(
      c.toInt()
        .toChar()
    )
    c = r()
  }
  val s = sb.toString()
  sb.setLength(0)
  return s
}

fun main() {
  val a = s()
  val k = s()
  val aLen = a.length
  val kLen = k.length

  var found = false
  for (i in 0 until aLen) {
    var cnt = 0
    for (j in 0 until kLen) {
      val ai = i + j
      if (ai >= aLen) break
      if (k[j] != a[ai]) break
      else cnt++
    }
    if (cnt == kLen) {
      found = true
      break
    }
  }
  w(if (found) 1 else 0)
  O.flush()
}
