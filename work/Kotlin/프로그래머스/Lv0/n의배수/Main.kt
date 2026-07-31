package 프로그래머스.Lv0.n의배수

import util.validate

class Solution {

  fun solution(num: Int, n: Int): Int =
    if (num % n == 0) 1 else 0
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
  validate(s.solution(98, 2), 1)
  validate(s.solution(34, 3), 0)
}
