package 백준.Bronze.no2490

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 6
const val OBS = 6
val O = BufferedOutputStream(System.`out`, OBS)
val I = BufferedInputStream(System.`in`)
val IB = ByteArray(IBS)
var Ii = 0
var Il = 0
const val EOF = -1

fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

val NUM = 48..57
fun i(): Int {
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

fun main() {
  val ans = ByteArray(6) { 10 }

  repeat(3) {
    var cnt = 0
    repeat(4) {
      if (i() == 0) cnt++
    }

    ans[2 * it] = (69 -
      if (cnt > 0) 5 - cnt
      else 0).toByte()
  }
  O.write(ans)
  O.flush()
}
