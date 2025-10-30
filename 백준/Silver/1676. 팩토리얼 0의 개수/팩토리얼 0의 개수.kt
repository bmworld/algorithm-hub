import java.io.BufferedInputStream
val F =
    IntArray(501).also {
      var cnt0 = 0
      for (i in 1..500) {
        var v = i
        var cnt5 = 0
        while (v % 5 == 0) {
          cnt5++
          v /= 5
        }
        var cnt2 = 0
        while (v % 2 == 0) {
          cnt2++
          v /= 2
        }
        cnt0 += cnt5
        it[i] = cnt0
      }
    }

fun main() {
  val n = readInt()
  print(F[n])
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