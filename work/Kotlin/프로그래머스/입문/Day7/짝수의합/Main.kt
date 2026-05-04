package 프로그래머스.입문.Day7.짝수의합

class Solution {

  fun solution(n: Int): Int = n / 2 * (n / 2 + 1)
}

fun main() {
  val s = Solution()
  check(s.solution(2) == 2)
  check(s.solution(10) == 30)
  check(s.solution(1000) == 250_500)
}
