package 백준.Bronze.no17608

import java.io.BufferedInputStream

const val IBS = 1 shl 15
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
  val N = i()
  val a = IntArray(N) { i() }

  var max = 0
  repeat(N) {
    val i = N - (it + 1)
    val v = a[i]
    if (v > max) {
      max = v
      ans++
    }
  }

  print(ans)
}
