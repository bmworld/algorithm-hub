package 프로그래머스.입문.Day10.공던지기

class Solution {

  fun solution(frnds: IntArray, k: Int): Int = frnds[2 * (k - 1) % frnds.size]
}

fun main() {
  val s = Solution()
  check(s.solution(intArrayOf(1, 2, 3, 4), 2) == 3)
  check(s.solution(intArrayOf(1, 2, 3, 4, 5, 6), 5) == 3)
  check(s.solution(intArrayOf(1, 2, 3), 3) == 2)


}
