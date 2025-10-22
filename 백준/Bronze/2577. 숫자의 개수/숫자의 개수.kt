import java.io.BufferedInputStream

val IN = BufferedInputStream(System.`in`)

fun main() {
  // 100 ~ 1000 // 최대 1000_000_000
  var n = readInt() * readInt() * readInt()
  var n_0 = 0
  var n_1 = 0
  var n_2 = 0
  var n_3 = 0
  var n_4 = 0
  var n_5 = 0
  var n_6 = 0
  var n_7 = 0
  var n_8 = 0
  var n_9 = 0

  while (n > 0) {
    val v = n % 10
    when (v) {
      0 -> n_0++
      1 -> n_1++
      2 -> n_2++
      3 -> n_3++
      4 -> n_4++
      5 -> n_5++
      6 -> n_6++
      7 -> n_7++
      8 -> n_8++
      9 -> n_9++
    }
    n /= 10
  }

  println(n_0)
  println(n_1)
  println(n_2)
  println(n_3)
  println(n_4)
  println(n_5)
  println(n_6)
  println(n_7)
  println(n_8)
  println(n_9)
}

fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var n = 0
  while (c in '0'.code..'9'.code) {
    n = n * 10 + (c - '0'.code)
    c = IN.read()
  }
  return n
}