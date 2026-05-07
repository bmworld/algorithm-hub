package 프로그래머스.입문.Day14.삼육구게임

class Solution {

  fun solution(order: Int): Int {
    var ans = 0
    var x = order
    while (x > 0) {
      when (x % 10) {
        3, 6, 9 -> ans++
      }
      x /= 10
    }
    return ans
  }
}

fun main() {
  val s = Solution()
  check(s.solution(3) == 1)
  check(s.solution(33) == 2)
  check(s.solution(3689) == 3)
  check(s.solution(29423) == 2)
}
