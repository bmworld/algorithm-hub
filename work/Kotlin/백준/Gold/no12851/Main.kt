package 백준.Gold.no12851

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 7
private const val OBS = 1 shl 7
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = BufferedInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0
private const val EOF = -1

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
private val WB = ByteArray(WS + 1).also { it[WS] = 10 }
private fun w(
  num: Int,
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
  pos++
  O.write(WB, pos, WS - pos + 1)
}

private const val MAX = 100_000
fun main() {
  val n = i()
  val k = i()
  when {
    n >= k -> {
      w(n - k)
      w(1)
    }

    else -> {
      var min = k - n + 1
      var cnt = 0
      val l = if (n / 2 > 0) n / 2 else 0
      val r = if (k + 2 < MAX) k + 2 else MAX
      val inRange = l..r

      val timer = IntArray(MAX + 1) { min }
      val q = IntArray(MAX)
      var qh = 0
      var qt = 0
      q[qt++] = k
      timer[k] = 0

      while (qh < qt) {
        val pos = q[qh++]
        val t = timer[pos]

        when {
          pos == n -> {
            min = t
            cnt++
          }

          else -> {
            var np = pos / 2
            val nt = t + 1
            if (pos % 2 == 0 && np in inRange && timer[np] >= nt && nt <= min) {
              timer[np] = nt
              q[qt++] = np
            }

            np = pos + 1
            if (np in inRange && timer[np] >= nt && nt <= min) {
              timer[np] = nt
              q[qt++] = np
            }

            np = pos - 1
            if (np in inRange && timer[np] >= nt && nt <= min) {
              timer[np] = nt
              q[qt++] = np
            }
          }
        }
      }

      w(min)
      w(cnt)
    }
  }
  O.flush()
}
