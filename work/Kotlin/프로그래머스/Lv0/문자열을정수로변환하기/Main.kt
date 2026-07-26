package 프로그래머스.Lv0.문자열을정수로변환하기

import util.validate

class Solution {

  fun solution(n_str: String): Int {
    var ans = 0
    for (x in n_str) ans = ans * 10 + x.code - 48
    return ans
  }
}

/**
 * ```
 * [ME]
 *
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("10"), 10)
  validate(s.solution("12345"), 12345)
}
