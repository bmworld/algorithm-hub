package 프로그래머스.입문.Day21.삼각형의완성조건2

class Solution {

  fun solution(sides: IntArray): Int = 2 * minOf(sides[0], sides[1]) - 1
}

fun main() {
  val s = Solution()
  check(s.solution(intArrayOf(1, 2)) == 1)
  check(s.solution(intArrayOf(3, 6)) == 5)
  check(s.solution(intArrayOf(11, 7)) == 13)
}
