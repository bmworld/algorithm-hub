package 백준.Silver.no11652

import java.io.BufferedInputStream

const val IBS = 1 shl 17
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

fun main() {
  val N = i().toInt()
  val map = HashMap<Long, Int>()
  var maxCnt = 0
  var ans = Long.MIN_VALUE
  repeat(N) {
    val v = i()
    val prv = map[v] ?: 0
    map[v] = (prv + 1).also {
      if (it > maxCnt) {
        maxCnt = it
        ans = v
      } else if (it == maxCnt && v < ans) ans = v
    }
  }

  print(ans)
}

// 2^62 = 4611686018427387904
