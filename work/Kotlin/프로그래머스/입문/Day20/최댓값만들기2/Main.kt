package 프로그래머스.입문.Day20.최댓값만들기2


class Solution {

  fun solution(a: IntArray): Int {
    var m1 = 0
    var m2 = 0
    var M1 = 0
    var M2 = 0

    for (x in a) {
      if (x < 0) {
        if (x < m1) {
          m2 = m1
          m1 = x
        } else if (x < m2) m2 = x
      } else {
        if (x > M1) {
          M2 = M1
          M1 = x
        } else if (x > M2) M2 = x
      }
    }
    return maxOf(m1 * m2, M1 * M2)
  }
}

fun main() {
  val s = Solution()
  check(s.solution(intArrayOf(1, 2, -3, 4, -5)) == 15)
  check(s.solution(intArrayOf(0, -31, 24, 10, 1, 9)) == 240)
  check(s.solution(intArrayOf(10, 20, 30, 5, 5, 20, 5)) == 600)
}
