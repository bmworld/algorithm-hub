package 백준.Silver.no1158

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 4
const val OBS = 1 shl 16
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

const val WS = 10
val WB = ByteArray(WS + 2).also { it[WS] = 44; it[WS + 1] = 32 }
fun w(
  num: Int,
  end: Boolean
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
  O.write(WB, ++pos, WS - pos + if (end) 0 else 2)
}

fun main() {
  val N = i()
  val K = i()
  val a = mutableListOf<Int>().also {
    repeat(N) { i ->
      it.add(i + 1)
    }
  }

  var pos = 0
  var len = N
  val dist = K - 1
  O.write('<'.code)
  repeat(N) {
    val np = (pos + dist) % len
    w(a[np], it == N - 1)
    a.removeAt(np)
    pos = if (np == len - 1) 0 else np
    len--
  }
  O.write('>'.code)
  O.flush()
}
