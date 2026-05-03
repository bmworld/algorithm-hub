package 프로그래머스.입문.나이출력

class Solution {

  val thisYear = 2022
  fun solution(age: Int): Int = thisYear - age + 1
}

fun main() {
  val s = Solution()
  check(s.solution(40) == 1983)
  check(s.solution(23) == 2000)
}
