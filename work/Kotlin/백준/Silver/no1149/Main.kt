package 백준.Silver.no1149

import java.io.BufferedInputStream

fun main() {
  val bi = BufferedInputStream(System.`in`)
  val n = readInt(bi)
  val arr = Array(n) { Price(readInt(bi), readInt(bi), readInt(bi)) }
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

fun solveTo(arr: Array<Price>): Int {
  var min = 0

  // 특정 집은 좌우 집과 다른 색이어야 함
  for (price in arr) {
    println("price{${price.r}, ${price.g}, ${price.b}}")
  }

  return min
}

data class Price(val r: Int, val g: Int, val b: Int)

/** 테스트용 */
fun solution(arr: Array<Price>): Int {
  return solveTo(arr)
}
