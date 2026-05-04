package 프로그래머스.입문.Day8.순서쌍의개수

class Solution {

  fun solution(n: Int): Int {
    if (n == 1) return 1
    var ans = 2
    var d = 2

    while (d <= n / d) {
      if (n % d == 0) {
        val r = n / d
        ans += if (d == r) 1 else 2
      }
      d++
    }

    return ans
  }
}

fun main() {
  val s = Solution()
  check(s.solution(1) == 1)
  check(s.solution(2) == 2)
  check(s.solution(3) == 2)
  check(s.solution(4) == 3)
  check(s.solution(5) == 2)
  check(s.solution(9) == 3)
  check(s.solution(20) == 6)
  check(s.solution(100) == 9)
}
