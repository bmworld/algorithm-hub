package 프로그래머스.Lv0.정수찾기

import util.validate

class Solution {

  fun solution(num_list: IntArray, n: Int): Int {
    for (x in num_list) if (x == n) return 1
    return 0
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
  validate(s.solution(intArrayOf(1, 2, 3, 4, 5), 3), 1)
  validate(s.solution(intArrayOf(1, 2, 3, 4, 5), 20), 0)
}
