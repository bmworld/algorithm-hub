package 프로그래머스.입문.Day5.아이스아메리카노

class Solution {

  val price = 5_500
  fun solution(m: Int): IntArray = intArrayOf(m / price, m % price)
}

fun main() {
  val s = Solution()
  val r = s.solution(5_500)
  check(r[0] == 1)
  check(r[1] == 0)

  val r2 = s.solution(15_000)
  check(r2[0] == 2)
  check(r2[1] == 4_000)

}
