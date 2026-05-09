package 프로그래머스.입문.Day17.자릿수더하기


class Solution {

  fun solution(n: Int): Int {
    var ans = 0
    var x = n
    while (x > 0) {
      ans += x % 10
      x /= 10
    }
    return ans
  }
}

fun main() {
  val s = Solution()
  check(s.solution(0) == 0)
  check(s.solution(123456789) == 45)
  check(s.solution(1234) == 10)
  check(s.solution(930211) == 16)
}
