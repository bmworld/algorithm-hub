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
  val n = arr.size
  

  var lt = 0
  while (lt < n) {
    var sum = arr[lt]
    for (i in lt + 1 until n) {
      val v = arr[i]
      val next = sum + v
      if (next > 0) {
        sum += v
        if (max < sum) max = sum
      } else break
    }
    var nextLt = lt + 1
    while (nextLt < n && arr[nextLt] < 0) {
      nextLt++
    }

    lt = nextLt
  }
  return max
}

/** 테스트용 */
fun solution(arr: IntArray): Int {
  return solveTo(arr)
}
