package 백준.Silver.no4948

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 8
const val OBS = 1 shl 6
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

const val WS = 10
val WB = ByteArray(WS + 1).also { it[WS] = 10 }
fun w(
  num: Int
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
  O.write(WB, ++pos, WS - pos + 1)
}

const val PRIME = 0
const val NON_PRIME = -1
const val INF = Int.MAX_VALUE
const val MAX_CASES = 1_000
fun main() {
  var max = 0
  var min = INF
  val cases = IntArray(MAX_CASES)
  var caseCnt = 0
  while (true) {
    val v = i()
    if (v == 0) break
    if (min > v) min = v
    if (max < 2 * v) max = 2 * v
    cases[caseCnt++] = v
  }

  val ch = IntArray(max + 1).also {
    it[0] = NON_PRIME
    if (max > 0) it[1] = NON_PRIME
  }

  var i = 2
  if (i * i <= max) for (j in i * i..max step i) if (ch[j] == PRIME) ch[j] = NON_PRIME
  i++

  while (i * i <= max) {
    for (j in i * i..max step i) if (ch[j] == PRIME) ch[j] = NON_PRIME
    i += 2
  }

  if (min != INF) ch[min] = 0
  repeat(max - min) {
    var v = min + 1 + it
    ch[v] = ch[v - 1] + if (ch[v] == PRIME) 1 else 0
  }

  repeat(caseCnt) {
    val l = cases[it]
    w(ch[2 * l] - ch[l])
  }

  O.flush()
}
