package 백준.Bronze.no28702

import java.io.BufferedInputStream

val IN = BufferedInputStream(System.`in`)

fun main() {
  var xi = 0
  var xv = 0
  for (i in 1..3) {
    val v = readInt()
    if (v <= 0) continue
    if (xv != 0) break
    xv = v
    xi = i
  }

  val x = xv + (4 - xi)
  print(if (x % 15 == 0) "FizzBuzz" else if (x % 3 == 0) "Fizz" else if (x % 5 == 0) "Buzz" else x)
}

private fun readInt(): Int {
  var c = IN.read()
  while (c !in 48..57) {
    c = IN.read()
    if (c == ' '.code || c == '\n'.code || c == '\r'.code) return -1 // 다음 띄어쓰기에서 끊음
  }
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = IN.read()
  }
  return n
}

// Fizz 980803 980804 FizzBuzz 980806
