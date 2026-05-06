package 프로그래머스.입문.Day11.팩토리얼

class Solution {

  fun solution(n: Int): Int {
    var v = 1
    var i = 1
    while (v < n) v *= ++i
    return if (v <= n) i else i - 1
  }
}

fun main() {
  val s = Solution()
  check(s.solution(3_628_800) == 10)
  check(s.solution(3_628_799) == 9)
  check(s.solution(7) == 3)
}
