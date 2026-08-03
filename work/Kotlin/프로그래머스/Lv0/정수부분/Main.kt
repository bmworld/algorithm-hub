package 프로그래머스.Lv0.정수부분

import util.validate

class Solution {

  fun solution(flo: Double): Int = flo.toInt()
}

/**
 * ```
 * [ME]
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(1.42), 1)
  validate(s.solution(1.99), 1)
  validate(s.solution(69.32), 69)
}
