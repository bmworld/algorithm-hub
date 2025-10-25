import java.io.BufferedInputStream
import kotlin.math.pow

fun main() {
  val L = readInt()
  val r = 31.0
  var sum = 0.0
  repeat(L) {
    val ch = IN.read() - 96 // a=1 ... z=26
    sum += ch * r.pow(it)
  }
  print(sum.toInt())
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