package 프로그래머스.Lv1.짝수와홀수

import util.validate

class Solution {

  fun solution(num: Int): String = if (num % 2 == 0) "Even" else "Odd"
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
  validate(s.solution(3), "Odd")
  validate(s.solution(2), "Even")
  validate(s.solution(4), "Even")

}
