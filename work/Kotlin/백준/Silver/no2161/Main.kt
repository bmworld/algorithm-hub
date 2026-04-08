package 백준.Silver.no2161

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 3
const val OBS = 1 shl 10
val O = BufferedOutputStream(System.out, OBS)
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
  var b: Byte
  while (r().also { b = it } in NUM || b == 45.toByte()) {
    when (b) {
      in NUM -> v = v * 10 + b - 48
      else -> s = -1
    }
  }
  return s * v
}

const val WS = 10
val WB = ByteArray(WS + 1).also { it[WS] = 32 }
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

fun main() {
  var N = i()
  val cards = IntArray(N) { it + 1 }

  var len = N
  while (len > 0) {
    var nxt = 0
    when {
      len % 2 == 1 -> {
        var toBeMoved = -1
        repeat(len) { cur ->
          when {
            cur % 2 == 0 -> w(cards[cur])
            else -> {
              val v = cards[cur]
              if (cur == 1) toBeMoved = v
              else cards[nxt++] = cards[cur]
            }
          }
        }
        cards[nxt] = toBeMoved
      }
      else -> {
        repeat(len) { cur ->
          when {
            cur % 2 == 0 -> w(cards[cur])
            else -> cards[nxt++] = cards[cur]
          }
        }
      }
    }

    len /= 2
  }

  O.flush()
}
