package 프로그래머스.Lv0.원소들의곱과합

import util.validate

class Solution {

  fun solution(num_list: IntArray): Int {
    var muply = 1
    var sum = 0
    for (x in num_list) {
      sum += x
      muply *= x
    }
    return if (muply < sum * sum) 1 else 0
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
  validate(s.solution(intArrayOf(3, 4, 5, 2, 1)), 1)
  validate(s.solution(intArrayOf(5, 7, 8, 3)), 0)
}
