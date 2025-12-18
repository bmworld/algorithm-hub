import java.io.BufferedInputStream
private val F = IntArray(501).also { f -> for (n in 1..500) f[n] = (n / 5) + (n / 25) + (n / 125) }
fun main() = print(F[readInt()])
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