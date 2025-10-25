import java.io.BufferedInputStream
import java.math.BigInteger

fun main() {
  val L = readInt()
  val r = BigInteger.valueOf(31)
  val M = BigInteger.valueOf(1234567891)
  var sum: BigInteger = BigInteger.ZERO
  repeat(L) {
    val ch = IN.read() - 96 // a=1 ... z=26
    sum = sum.add((BigInteger.valueOf(ch.toLong()).multiply(r.pow(it))))
  }
  print(sum.mod(M).toInt())
}


val IN = BufferedInputStream(System.`in`)

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = IN.read()
  }
  return n
}