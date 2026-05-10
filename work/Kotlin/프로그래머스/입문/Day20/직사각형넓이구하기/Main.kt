package 프로그래머스.입문.Day20.직사각형넓이구하기


class Solution {

  fun solution(dots: Array<IntArray>): Int {
    val d1 = dots[0]
    val d2 = dots[1]
    val d3 = dots[2]
    val d4 = dots[3]

    val MX = maxOf(d1[0], d2[0], d3[0], d4[0])
    val mX = minOf(d1[0], d2[0], d3[0], d4[0])
    val MY = maxOf(d1[1], d2[1], d3[1], d4[1])
    val mY = minOf(d1[1], d2[1], d3[1], d4[1])
    return (MX - mX) * (MY - mY)
  }
}

fun main() {
  val s = Solution()
  check(s.solution(
    arrayOf<IntArray>(intArrayOf(1, 1), intArrayOf(2, 1), intArrayOf(2, 2), intArrayOf(1, 2))) == 1)
  check(s.solution(
    arrayOf<IntArray>(intArrayOf(-1, -1), intArrayOf(1, 1), intArrayOf(1, -1),
      intArrayOf(-1, 1))) == 4)
}
