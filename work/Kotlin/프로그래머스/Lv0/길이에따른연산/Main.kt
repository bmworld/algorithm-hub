package 프로그래머스.Lv0.길이에따른연산

import util.validate

class Solution {

  fun solution(num_list: IntArray): Int {
    val N = num_list.size
    var ans = if (N <= 10) 1 else 0

    when {
      N <= 10 -> repeat(N) {
        ans *= num_list[it]
      }
      else -> repeat(N) {
        ans += num_list[it]
      }
    }
    return ans
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
  validate(s.solution(intArrayOf(3, 4, 5, 2, 5, 4, 6, 7, 3, 7, 2, 2, 1)), 51)
  validate(s.solution(intArrayOf(2, 3, 4, 5)), 120)
}
