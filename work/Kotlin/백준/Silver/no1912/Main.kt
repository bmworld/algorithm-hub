package 백준.Silver.no1912

import java.io.BufferedInputStream

fun main() {
  val bi = BufferedInputStream(System.`in`)
  val n = readInt(bi)
  val arr = IntArray(n) { readInt(bi) }
  println(solveTo(arr))
}

private fun readInt(input: BufferedInputStream): Int {
  var c = input.read()
  while (c <= 32) c = input.read() // skip spaces
  var sign = 1
  if (c == '-'.code) { // 부호 처리
    sign = -1
    c = input.read()
  }
  var n = 0
  while (c in '0'.code..'9'.code) {
    n = n * 10 + (c - '0'.code)
    c = input.read()
  }
  return n * sign
}

fun solveTo(arr: IntArray): Int {
  var max = arr[0]
  var acc = arr[0]
  for (i in 1 until arr.size) {
    val v = arr[i]
    val next = acc + v
    acc = if (next > v) next else v
    max = if (max > acc) max else acc
  }
  return max
}

/** 테스트용 */
fun solution(arr: IntArray): Int {
  return solveTo(arr)
}
