package 프로그래머스.Lv0.홀수vs짝수

import util.validate

class Solution {

  fun solution(num_list: IntArray): Int {
    var odd = 0
    var even = 0
    for (i in num_list.indices) {
      val x = num_list[i]
      if (i % 2 == 0) even += x
      else odd += x
    }
    return maxOf(odd, even)
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
  validate(s.solution(intArrayOf(4, 2, 6, 1, 7, 6)), 17)
  validate(s.solution(intArrayOf(-1, 2, 5, 6, 3)), 8)
  validate(s.solution(intArrayOf(1, 2, 4, 1)), 5)
}
