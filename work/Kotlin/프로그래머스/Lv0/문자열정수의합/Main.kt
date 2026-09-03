package 프로그래머스.Lv0.문자열정수의합

import util.validate

class Solution {
  companion object {

    const val ZERO = 48
  }

  fun solution(num_str: String): Int {
    var ans = 0
    for (x in num_str) ans += x.code - ZERO
    return ans
  }
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
  validate(s.solution("123456789"), 45)
  validate(s.solution("10"), 1)
}
