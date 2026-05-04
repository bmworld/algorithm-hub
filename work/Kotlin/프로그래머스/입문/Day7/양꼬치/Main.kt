package 프로그래머스.입문.Day7.양꼬치

class Solution {

  val sPrice = 12_000
  val bPrice = 2_000
  val serviceCnt = 10
  fun solution(s: Int, k: Int): Int {
    return s * sPrice + maxOf(0, k - s / serviceCnt) * bPrice
  }
}

fun main() {
  val s = Solution()
  check(s.solution(10, 3) == 124_000)
  check(s.solution(10, 0) == 120_000)
  check(s.solution(64, 6) == 768_000)
}
