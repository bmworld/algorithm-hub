package 백준.Bronze.no2501

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.util.*

const val IBS = 5
const val OBS = 5
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
  O.write(WB, ++pos, WS - pos)
}

fun main() {
  val N = i()
  val K = i()
  if (K == 1) {
    w(1)
    O.flush()
    return
  }

  val q = PriorityQueue<Int>()
  q.add(1)
  q.add(N)
  var cnt = 2
  var d = 2
  while (d * d <= N) {
    if (N % d == 0) {
      q.add(d)
      cnt++
      (N / d).also {
        if (it != d) {
          q.add(it)
          cnt++
        }
      }
    }
    d++
  }

  w(
    if (cnt >= K) {
      repeat(K - 1) { q.poll() }
      q.poll()
    } else 0
  )

  O.flush()
}
