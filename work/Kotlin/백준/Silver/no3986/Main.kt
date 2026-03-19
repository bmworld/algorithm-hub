package 백준.Silver.no3986

import java.io.BufferedInputStream

const val IBS = 1 shl 20
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
const val EOL: Byte = 10
const val EMPTY: Byte = -1
const val STACK_LIMIT = 4
fun main() {

  var ans = 0
  val stack = ByteArray(STACK_LIMIT)

  repeat(i()) {
    var prv: Byte = EMPTY
    var cur: Byte

    var stacked = 0
    var aCnt = 0
    var bCnt = 0
    while (r().also { cur = it; if (prv == EMPTY) prv = it } >= EOL) {
      val changed = cur != prv
      var merged = false
      if (changed && stacked < STACK_LIMIT) {
        when (prv) {
          A -> {
            if (aCnt % 2 != 0) stack[stacked++] = prv
            aCnt = 0
          }
          B -> {
            if (bCnt % 2 != 0) stack[stacked++] = prv
            bCnt = 0
          }
        }

        if (stacked > 0 && stack[stacked - 1] == cur) {
          stacked--
          merged = true
        }
      }


      when (cur) {
        A -> if (!merged) aCnt++
        B -> if (!merged) bCnt++
        EOL -> break
      }
      prv = cur
    }

    if (stacked == 0) ans++
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
5
AA
AABB
ABBA
BAABBB
BBBABBABBB
BABABBAABAABBBABAB
OUT
6

 */

// BABABBAABAABBBABAB
//AAAAAAAAAABAAABAB
