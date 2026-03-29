package 백준.Bronze.no1919

import java.io.BufferedInputStream

const val IBS = 1 shl 11
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

const val a: Byte = 97
const val ALPHABETS = 26
fun main() {

  var b: Byte
  val w1 = IntArray(ALPHABETS)
  while (r().also { b = it } >= a) w1[b - a]++

  val w2 = IntArray(ALPHABETS)
  while (r().also { b = it } >= a) w2[b - a]++

  var ans = 0
  repeat(ALPHABETS) {
    var diff = w2[it] - w1[it]
    if (diff < 0) diff = -diff
    ans += diff
  }

  print(ans)
}
