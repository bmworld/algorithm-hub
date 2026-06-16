package 프로그래머스.Lv1.평균구하기

import util.validate

class Solution {

  fun solution(arr: IntArray): Double {
    var sum = 0.0
    for (x in arr) sum += x
    return sum / arr.size
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
  validate(s.solution(intArrayOf(1, 2, 3, 4)), 2.5)
  validate(s.solution(intArrayOf(5, 5)), 5.0)
  validate(s.solution(intArrayOf(1, 2)), 1.5)

}
