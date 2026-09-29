package 프로그래머스.Lv0.가까운1찾기

import util.validate

class Solution {

  fun solution(arr: IntArray, idx: Int): Int {
    for (i in idx until arr.size) if (arr[i] == 1) return i
    return -1
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
  validate(s.solution(intArrayOf(0, 0, 0, 1), 1), 3)
  validate(s.solution(intArrayOf(1, 0, 0, 1, 0, 0), 4), -1)
  validate(s.solution(intArrayOf(1, 0, 0, 1, 1), 3), 3)
}
