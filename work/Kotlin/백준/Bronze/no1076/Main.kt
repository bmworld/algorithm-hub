package 백준.Bronze.no1076

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 21
const val OBS = 20
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

val c = IntArray(6)
const val a: Byte = 97
fun getColor(): Int {
  var b: Int
  var len = 0
  while (r().also { b = it.toInt() } >= a) c[len++] = b

  return when {
    c[0] == 'b'.code -> if (c[1] == 'r'.code) 1 else if (len == 5) 0 else 6
    c[0] == 'r'.code -> 2
    c[0] == 'o'.code -> 3
    c[0] == 'y'.code -> 4
    c[0] == 'g'.code -> if (len == 5) 5 else 8
    c[0] == 'v'.code -> 7
    c[0] == 'w'.code -> 9
    else -> -1
  }
}

const val WS = 20
val WB = ByteArray(WS)
fun w(
  num: Long,
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

val multipier = intArrayOf(
  1,
  10,
  100,
  1_000,
  10_000,
  100_000,
  1_000_000,
  10_000_000,
  100_000_000,
  1_000_000_000
)

fun main() {
  var ans = 0L
  repeat(3) {
    val v = getColor()
    when (it) {
      0 -> ans += v
      1 -> ans = ans * 10 + v
      else -> ans *= multipier[v]
    }
  }
  w(ans)
  O.flush()
}
