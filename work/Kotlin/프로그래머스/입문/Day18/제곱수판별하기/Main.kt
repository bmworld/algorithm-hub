package 프로그래머스.입문.Day18.제곱수판별하기

class Solution {

  fun solution(n: Int): Int {
    var d = 1
    while (d < n / d) d++
    return if (d * d == n) 1 else 2
  }
}

fun main() {
  val s = Solution()
  check(s.solution(1) == 1)
  check(s.solution(2) == 2)
  check(s.solution(3) == 2)
  check(s.solution(144) == 1)
  check(s.solution(976) == 2)
}
