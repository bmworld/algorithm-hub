package 프로그래머스.Lv0.조건에맞게수열변환하기

import util.validate

class Solution {

  fun solution(arr: IntArray, k: Int): IntArray =
    if (k % 2 == 1) IntArray(arr.size) { arr[it] * k }
    else IntArray(arr.size) { arr[it] + k }
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
  validate(
    s.solution(
      intArrayOf(1, 2, 3, 100, 99, 98), 3),
    intArrayOf(3, 6, 9, 300, 297, 294)
  )

  validate(
    s.solution(
      intArrayOf(1, 2, 3, 100, 99, 98), 2),
    intArrayOf(3, 4, 5, 102, 101, 100)
  )

}
