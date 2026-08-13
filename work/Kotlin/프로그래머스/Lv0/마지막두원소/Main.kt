package 프로그래머스.Lv0.마지막두원소

import util.validate

class Solution {

  fun solution(num_list: IntArray): IntArray {
    val N = num_list.size
    return IntArray(N + 1) {
      if (it < N) num_list[it] else {
        val last1 = num_list[N - 1]
        val last2 = num_list[N - 2]
        if (last1 > last2) last1 - last2 else last1 * 2
      }
    }
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
  validate(s.solution(intArrayOf(2, 1, 6)), intArrayOf(2, 1, 6, 5))
  validate(s.solution(intArrayOf(5, 2, 1, 7, 5)), intArrayOf(5, 2, 1, 7, 5, 10))
}
