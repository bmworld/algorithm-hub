package 프로그래머스.Lv0.날짜비교하기

import util.validate

class Solution {

  fun solution(date1: IntArray, date2: IntArray): Int {
    for (i in date1.indices)
      if (date1[i] < date2[i]) return 1


    return 0
  }
}

/**
 * ```
 * [ME]
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    intArrayOf(2021, 12, 28),
    intArrayOf(2021, 12, 29),
  ), 1)

  validate(s.solution(
    intArrayOf(1024, 10, 24),
    intArrayOf(1024, 10, 24),
  ), 0)

  validate(s.solution(
    intArrayOf(1024, 10, 24),
    intArrayOf(1024, 10, 23),
  ), 0)
}
