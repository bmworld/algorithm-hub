package 백준.Silver.no1057

import java.io.BufferedInputStream

const val IBS = 1 shl 5
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

fun main() {
  var N = i()
  var A = i()
  var B = i()
  if (A > B) {
    val tmp = A
    A = B
    B = tmp
  }

  var round = 1
  while (N > 1) {
    val isAOdd = A % 2 == 1
    if (isAOdd && A + 1 == B) return print(round)
    round++
    A = divide(A)
    B = divide(B)
    N = divide(N)
  }

  print(-1)
}

fun divide(v: Int): Int = (if (v % 2 == 1) (v + 1) else v) / 2
