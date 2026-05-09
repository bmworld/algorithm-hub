package 프로그래머스.입문.Day18.세균증식

class Solution {

  fun solution(n: Int, t: Int): Int = n * 1 shl t
}

fun main() {
  val s = Solution()
  check(s.solution(2, 10) == 2048)
  check(s.solution(7, 15) == 229_376)
}
