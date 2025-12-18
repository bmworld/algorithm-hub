import java.io.BufferedInputStream
fun main() {
  val n = readInt()
  val tReqs = IntArray(6) { readInt() }
  val T = readInt()
  val P = readInt()
  var tsum = 0
  for (req in tReqs) tsum += (req + T - 1) / T
  println(tsum)
  print(n / P)
  print(' ')
  print(n % P)
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