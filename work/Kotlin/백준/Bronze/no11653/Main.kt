package 백준.Bronze.no11653

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.util.*

const val IBS = 8
const val OBS = 1 shl 8
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

const val SEP = 100
fun main() {
  var N = i()
  val q = PriorityQueue<Int>()

  var i = 2
  while (i * i <= N) {
    var cnt = 0
    while (N > 1 && N % i == 0) {
      N /= i
      cnt++
    }
    if (cnt > 0) q.add(i * SEP + cnt)
    i++
  }
  if (N > 1) q.add(N * SEP + 1)


  while (q.isNotEmpty()) {
    val e = q.poll()
    val v = e / SEP
    val cnt = e % SEP
    repeat(cnt) {
      w(v)
    }
  }

  O.flush()
}
