package 프로그래머스.Lv0.배열만들기4

import util.validate

class Solution {

  fun solution(arr: IntArray): IntArray {
    val stk = IntArray(arr.size)
    var i = 0
    for (x in arr) {
      when {
        i == 0 || stk[i - 1] < x -> stk[i++] = x
        else -> stk[i - 1] = x
      }
    }
    return stk.copyOf(i)
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
  validate(s.solution(intArrayOf(1, 4, 2, 5, 3)), intArrayOf(1, 2, 3))
}
