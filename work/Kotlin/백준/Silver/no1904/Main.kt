package 백준.Silver.no1904

import java.io.BufferedInputStream

fun main() {
  val n = readInt(BufferedInputStream(System.`in`))
  println(c[n])
}

val c =
    IntArray(1_000_001).apply {
      this[1] = 1
      this[2] = 2
      for (i in 3..1_000_000) {
        this[i] = (this[i - 1] + this[i - 2]) % 15746
      }
    }

private fun readInt(input: BufferedInputStream): Int {
  var c = input.read()
  while (c <= 32) c = input.read()
  var n = 0
  while (c in 48..57) { // '0' ~ '9'
    n = n * 10 + (c - 48)
    c = input.read()
  }
  return n
}

/**
 * @param n 이진수의 자리수(1 <= n <= 1,000,000)
 * @return 타일(00, 1)로 만들 수 있는 모든 경우의 수
 */
fun solveTo(n: Int, out: Appendable) {
  out.append(c[n].toString())
}

/** 테스트용 */
fun solution(n: Int): Int {
  val sb = StringBuilder()
  solveTo(n, sb)
  return sb.toString().toInt()
}
