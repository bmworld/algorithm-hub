package 프로그래머스.입문.Day19.중복된숫자개수


class Solution {

  fun solution(a: IntArray, n: Int): Int {
    var ans = 0
    for (x in a) if (x == n) ans++
    return ans
  }
}

fun main() {
  val s = Solution()
  check(s.solution(intArrayOf(1, 1, 2, 3, 4, 5), 1) == 2)
  check(s.solution(intArrayOf(0, 2, 3, 4), 1) == 0)
}
