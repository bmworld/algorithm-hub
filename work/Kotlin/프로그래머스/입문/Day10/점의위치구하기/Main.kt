package 프로그래머스.입문.Day10.점의위치구하기

class Solution {

  fun solution(dot: IntArray): Int {
    val x = dot[0]
    val y = dot[1]
    return when {
      x > 0 && y > 0 -> 1
      x < 0 && y > 0 -> 2
      x < 0 && y < 0 -> 3
      else -> 4
    }
  }
}

fun main() {
  val s = Solution()
  check(s.solution(intArrayOf(1, 1)).also { println(it) } == 1)
  check(s.solution(intArrayOf(-1, 1)).also { println(it) } == 2)
  check(s.solution(intArrayOf(-1, -1)).also { println(it) } == 3)
  check(s.solution(intArrayOf(1, -1)).also { println(it) } == 4)
}
