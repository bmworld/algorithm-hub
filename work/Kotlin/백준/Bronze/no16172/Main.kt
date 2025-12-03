package 백준.Bronze.no16172

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 200_000
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
  val a = ByteArray(200_000)
  var c: Byte
  var len = 0
  while (r().also { c = it } >= 32.toByte()) {
    when (c) {
      in NUM -> continue
      else -> a[len++] = c
    }
  }

  var found = true
  var i = 0
  while (found && i < len && r().also { c = it } >= 65.toByte()) { //    println("c=$c, a[$i]=${a[i]}")
    if (i == 0) {
      while (i < len) {
        if (a[i++] == c) {
          found = true
          break
        } else found = false
      }
      continue
    }

    if (a[i++] != c) {
      found = false
      break
    }
  }

  w(if (found) 1 else 0)
  O.flush()
}
