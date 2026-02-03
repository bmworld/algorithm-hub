package 백준.Silver.no2407

import java.io.BufferedInputStream
import java.math.BigInteger

const val IBS = 1 shl 3
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
  var c: Byte
  while (r().also { c = it } in NUM || c == 45.toByte()) {
    when (c) {
      in NUM -> v = v * 10 + c - 48
      else -> s = -1
    }
  }
  return s * v
}

fun main() {
  val N = i()
  var M = i()
  if (M > N / 2) M = N - M

  var num = BigInteger.ONE
  var den = BigInteger.ONE
  repeat(M) {
    var m = it + 1
    var n = N - it
    if (n % m == 0) {
      num = num.multiply((n / m).toBigInteger())
    } else {
      num = num.multiply(n.toBigInteger())
      den = den.multiply(m.toBigInteger())
    }
  }

  print(num.divide(den))
}
