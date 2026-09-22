package 프로그래머스.Lv0.n보다커질때까지더하기

import util.validate

class Solution {

  fun solution(numbers: IntArray, n: Int): Int {
    var sum = 0
    for (x in numbers)
      sum = (sum + x).also { if (it > n) return it }
    return sum
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
  validate(s.solution(intArrayOf(34, 5, 71, 29, 100, 34), 123), 139)
  validate(s.solution(intArrayOf(58, 44, 27, 10, 100), 139), 239)
}
