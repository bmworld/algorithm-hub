package 프로그래머스.Lv0.조건에맞게수열변환하기1

import util.validate

class Solution {

  fun solution(arr: IntArray): IntArray =
    IntArray(arr.size) {
      val x = arr[it]
      when {
        x >= 50 && x % 2 == 0 -> x / 2
        x < 50 && x % 2 == 1 -> x * 2
        else -> x
      }
    }
}

/**
 * ```
 * [ME]
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(
      intArrayOf(1, 2, 3, 100, 99, 98)),
    intArrayOf(2, 2, 6, 50, 99, 49)
  )

}
