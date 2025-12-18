import java.io.BufferedInputStream

fun main() {
  val n = readInt()
  var cnt = 0
  repeat(n) {
    val v = readInt()
    if (isPrime(v)) cnt++
  }
  print(cnt)
}

private fun isPrime(v: Int): Boolean {
  if (v < 2) return false
  var i = 2
  while (i * i <= v) { // ch: 4~
    if (v % i == 0) return false
    i++
  }

  return true
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