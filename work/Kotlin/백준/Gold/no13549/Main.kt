package 백준.Gold.no13549

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.util.*

private const val IBS = 16
private const val OBS = 16
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
private val WB = ByteArray(WS)
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
  O.write(WB, pos, WS - pos)
}

fun main() {
  val n = i()
  val k = i()
  w(
    when {
    n >= k -> n - k

    else -> {
      var min = k - n
      val timer = IntArray(k + 1) { min }
      val q = PriorityQueue<Int>()
      q.add(k)
      timer[k] = 0
      while (q.isNotEmpty()) {
        val pos = q.poll()
        val t = timer[pos]
        when {
          pos == n -> {
            min = t
            break
          }

          else -> {
            var np = pos / 2
            if (pos % 2 == 0 && np in 0..k && timer[np] > t) {

              timer[np] = t
              q.add(np)
            }

            val nt = t + 1
            np = pos + 1
            if (np in n..k && timer[np] > nt) {
              timer[np] = nt
              q.add(np)
            }

            np = pos - 1
            if (np in n - 1..k && timer[np] > nt) {
              timer[np] = nt
              q.add(np)
            }
          }
        }
      }
      min
    }
  })
  O.flush()
}

//         println("pos = ${pos}, t= $t")
