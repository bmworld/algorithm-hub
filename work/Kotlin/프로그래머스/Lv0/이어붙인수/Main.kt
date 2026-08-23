package 프로그래머스.Lv0.이어붙인수

import util.validate

class Solution {

  fun solution(num_list: IntArray): Int {
    var odds = 0
    var evens = 0
    for (x in num_list)
      if (x % 2 == 0) evens = evens * 10 + x
      else odds = odds * 10 + x
    return odds + evens
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
  validate(s.solution(intArrayOf(3, 4, 5, 2, 1)), 393)
  validate(s.solution(intArrayOf(5, 7, 8, 3)), 581)
}
