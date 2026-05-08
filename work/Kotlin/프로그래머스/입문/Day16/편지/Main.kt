package 프로그래머스.입문.Day16.편지


class Solution {

  fun solution(str: String): Int = str.length * 2
}

fun main() {
  val s = Solution()
  check(s.solution("happy birthday!") == 30)
  check(s.solution("I love you~") == 22)
}
