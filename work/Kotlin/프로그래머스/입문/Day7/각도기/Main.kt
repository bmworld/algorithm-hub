package 프로그래머스.입문.Day7.각도기

class Solution {

  fun solution(angle: Int): Int = when (angle) {
    in 0 until 90 -> 1
    90 -> 2
    in 91 until 180 -> 3
    180 -> 4
    else -> -1
  }
}

fun main() {
  val s = Solution()
  check(s.solution(70) == 1)
  check(s.solution(90) == 2)
  check(s.solution(91) == 3)
  check(s.solution(180) == 4)
  check(s.solution(189) == -1)
}
