package 백준.Bronze.no9506

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.util.*

const val IBS = 1 shl 10
const val OBS = 1 shl 15
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
val WB = ByteArray(WS)
fun w(num: Int) {
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
  O.write(WB, ++pos, WS - pos)
}


val EQUAL = byteArrayOf(32, 61, 32)
val PLUS = byteArrayOf(32, 43, 32)
val IS_NOT_PERFECT = " is NOT perfect.\n".toByteArray()
fun main() {
  val q = PriorityQueue<Int>()
  while (true) {
    val v = i()
    if (v == -1) break
    var sum = 1.also { q.add(it) }
    var d = 2
    while (d * d <= v) {
      if (v % d == 0) {
        sum += d.also { q.add(it) }
        if (d * d != v) sum += (v / d).also { q.add(it) }
      }
      d++
    }

    if (sum == v) {
      w(v)
      O.write(EQUAL)
      val cnt = q.size
      repeat(cnt) {
        w(q.poll())
        if (it + 1 == cnt) O.write(10)
        else O.write(PLUS)
      }
    } else {
      w(v)
      O.write(IS_NOT_PERFECT)
    }
    q.clear()
  }

  O.flush()
}
