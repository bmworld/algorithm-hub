package 백준.Silver.no4134

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 10
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
fun i(): Long {
  var v = 0L
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

const val WS = 20
val WB = ByteArray(WS + 1).also { it[WS] = 10 }
fun w(
  num: Long
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
  repeat(i().toInt()) {
    var v = i()
    if (v <= 2) w(2)
    else {
      while (true) {
        if (isPrime(v)) {
          w(v)
          break
        } else if (v % 2 == 0L) v++
        else v += 2
      }
    }
  }
  O.flush()
}

fun isPrime(v: Long): Boolean {
  var d = 2
  if (v < d) return false
  while (d * d <= v) {
    if (v % d == 0L) return false
    if (d == 2) d++
    else d += 2
  }
  return true
}
