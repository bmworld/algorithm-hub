package 프로그래머스.입문.Day19.머쓱이보다키큰사람


class Solution {

  fun solution(a: IntArray, n: Int): Int {
    var ans = 0
    for (x in a) if (x > n) ans++
    return ans
  }
}

fun main() {
  val s = Solution()
  check(s.solution(intArrayOf(149, 180, 192, 170), 167) == 3)
  check(s.solution(intArrayOf(180, 120, 140), 190) == 0)
}
