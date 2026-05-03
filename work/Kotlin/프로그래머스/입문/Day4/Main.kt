package 프로그래머스.입문.Day4.피자나눠먹기1

const val SLICES = 7

class Solution {

  fun solution(n: Int): Int = (n + SLICES - 1) / SLICES
}

fun main() {
  val s = Solution()
  check(s.solution(1) == 1)
  check(s.solution(7) == 1)
  check(s.solution(8) == 2)
  check(s.solution(14) == 2)
  check(s.solution(15) == 3)
}
