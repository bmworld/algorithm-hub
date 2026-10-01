package 프로그래머스.Lv0.배열만들기1

import util.validate

class Solution {

  fun solution(n: Int, k: Int): IntArray = IntArray(n / k) { k * (it + 1) }
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
  validate(s.solution(10, 3), intArrayOf(3, 6, 9))
  validate(s.solution(5, 3), intArrayOf(3))
}
