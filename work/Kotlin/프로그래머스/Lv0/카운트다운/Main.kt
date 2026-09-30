package 프로그래머스.Lv0.카운트다운

import util.validate

class Solution {

  fun solution(start_num: Int, end_num: Int): IntArray =
    IntArray(start_num - end_num + 1) { start_num - it }
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
  validate(s.solution(10, 3), intArrayOf(10, 9, 8, 7, 6, 5, 4, 3))
}
