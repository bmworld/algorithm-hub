package 백준.Silver.no14501

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.util.*

const val IBS = 1 shl 6
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

const val PaySEP = 10_000
const val frSEP = 100
const val ToSEP = frSEP * PaySEP
fun main() {

  val N = i()
  val dp = IntArray(N + 1)
  val toCnts = IntArray(N + 1)
  val q = PriorityQueue<Int>()

  repeat(N) {
    val fr = it + 1
    val to = fr - 1 + i()
    val pay = i()
    if (to > N) return@repeat
    toCnts[to]++
    q.add(to * ToSEP + fr * PaySEP + pay)
  }

  repeat(N) {
    val to = it + 1
    val prv = dp[to - 1]
    var max = prv
    repeat(toCnts[to]) {
      val e = q.poll()
      val fp = e % ToSEP
      val fr = fp / PaySEP
      val pay = fp % PaySEP
      val nxt = dp[fr - 1] + pay
      max = maxOf(max, nxt)
    }
    dp[to] = max
  }

  w(dp[N])
  O.flush()
}
