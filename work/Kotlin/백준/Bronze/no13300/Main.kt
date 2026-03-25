package 백준.Bronze.no13300

import java.io.BufferedInputStream

const val IBS = 1 shl 12
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

  var ans = 0
  val CASES = 12

  val N = i()
  val K = i()
  val CNTS = IntArray(CASES)
  repeat(N) {
    val gender = i()
    val degree = i()
    CNTS[2 * (degree - 1) + gender]++
  }

  repeat(CASES) {
    ans += (CNTS[it] + K - 1) / K
  }

  print(ans)
}
