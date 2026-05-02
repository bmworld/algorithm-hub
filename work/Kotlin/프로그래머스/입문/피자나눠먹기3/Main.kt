package 프로그래머스.입문.피자나눠먹기3

class Solution {

  fun solution(slice: Int, n: Int): Int = (n + slice - 1) / slice
}

fun main() {
  val s = Solution()
  check(s.solution(2, 10) == 5)
  check(s.solution(7, 7) == 1)
  check(s.solution(7, 8) == 2)
  check(s.solution(7, 10) == 2)
  check(s.solution(4, 12) == 3)
  check(s.solution(4, 13) == 4)
}
