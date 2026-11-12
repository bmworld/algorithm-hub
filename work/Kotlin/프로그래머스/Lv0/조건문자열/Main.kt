package 프로그래머스.Lv0.조건문자열

import util.validate

class Solution {

  fun solution(ineq: String, eq: String, n: Int, m: Int): Int =
    if (when {
        eq == "=" -> if (ineq == ">") n >= m else n <= m
        else -> if (ineq == ">") n > m else n < m
      }
    ) 1 else 0
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
  validate(s.solution("<", "!", 20, 20), 0)
  validate(s.solution("<", "=", 20, 21), 1)
  validate(s.solution("<", "=", 20, 19), 0)
  validate(s.solution(">", "=", 20, 20), 1)
  validate(s.solution(">", "=", 20, 19), 1)
  validate(s.solution(">", "!", 20, 19), 1)
}
