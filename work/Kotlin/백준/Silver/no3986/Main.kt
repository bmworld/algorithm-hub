package 백준.Silver.no3986

import java.io.BufferedInputStream

const val IBS = 1 shl 16
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

const val A: Byte = 65
const val B: Byte = 66
const val EMPTY = 1
const val MAX_LEN = 100_000
fun main() {
  var b: Byte

  var ans = 0
  val stack = ByteArray(MAX_LEN + 1)
  repeat(i()) {
    var pos = EMPTY
    while (r().also { b = it } >= A) {
      if (b == stack[pos - 1]) pos--
      else stack[pos++] = b
    }
    if (pos == EMPTY) ans++
  }

  print(ans)
}

/**

IN
5
ABA
ABAB
BBBBAAB
BBBAAABBA
BABBAABBBBBBBAABBB
OUT
0

IN
6
AA
AABB
ABBA
BAABBB
BBBABBABBB
BABABBAABAABBBABAB
OUT
6
 */
