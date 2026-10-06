package 프로그래머스.Lv0._9로나눈나머지

import util.validate

class Solution {
  companion object {

    const val ZERO = 48
  }

  fun solution(number: String): Int {
    var ans = 0
    for (x in number) ans += x.code - ZERO
    return ans % 9
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
  validate(s.solution("0"), 0)
  validate(s.solution("123"), 6)
  validate(s.solution("78720646226947352489"), 2)
}
