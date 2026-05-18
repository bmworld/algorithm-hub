package 프로그래머스.코딩기초트레이닝.공배수

import util.validate

class Solution {

  fun solution(number: Int, n: Int, m: Int): Int = if (number % n == 0 && number % m == 0) 1 else 0
}

/**
 * ```
 * ME:
 * ```
 *
 *
 * ```
 * RIVAL:
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(60, 2, 3), 1)
  validate(s.solution(55, 5, 3), 0)
  validate(s.solution(4, 2, 2), 1)
  validate(s.solution(4, 2, 3), 0)

}
