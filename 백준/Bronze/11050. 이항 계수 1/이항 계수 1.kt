import java.io.BufferedInputStream
private val IN = BufferedInputStream(System.`in`, 1 shl 20)
val F = IntArray(11).also {
      it[0] = 1
      for (i in 1..10) {
        it[i] = it[i - 1] * i
      }
    }
fun main() {
  val n = readInt()
  val k = readInt()
  print(F[n] / (F[k] * F[n - k]))
}
private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read() // filter
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = IN.read()
  }
  return n
}