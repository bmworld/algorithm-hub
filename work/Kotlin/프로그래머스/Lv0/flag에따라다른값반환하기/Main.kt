package 프로그래머스.Lv0.flag에따라다른값반환하기

import util.validate

class Solution {

  fun solution(a: Int, b: Int, flag: Boolean): Int =
    if (flag) a + b else a - b
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
  validate(s.solution(-4, 7, true), 3)
  validate(s.solution(-4, 7, false), -11)
}
