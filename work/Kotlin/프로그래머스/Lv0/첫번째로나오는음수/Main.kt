package 프로그래머스.Lv0.첫번째로나오는음수

import util.validate

class Solution {

  fun solution(a: IntArray): Int {
    for (i in a.indices) if (a[i] < 0) return i
    return -1
  }
}

/**
 * ```
 * [ME]
 * ```
 *
 *
 * ```
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(12, 4, 15, 46, 38, -2, 15)), 5)
  validate(s.solution(intArrayOf(1, 1, 4)), -1)
}
