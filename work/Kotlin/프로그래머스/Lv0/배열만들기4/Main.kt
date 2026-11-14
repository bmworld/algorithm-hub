package 프로그래머스.Lv0.배열만들기4

import util.validate

class Solution {

  fun solution(arr: IntArray): IntArray {
    val stk = IntArray(arr.size)
    var i = 0
    for (x in arr) {
      while (i > 0 && stk[i - 1] >= x) i--
      stk[i++] = x
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
  validate(s.solution(intArrayOf(1, 4, 3, 2)), intArrayOf(1, 2))
  validate(s.solution(intArrayOf(4, 1)), intArrayOf(1))

  // CASE 1: 여러 원소를 제거해야 하는 경우
  validate(s.solution(intArrayOf(3, 5, 2)), intArrayOf(2))
// 실제: [3, 2]

// CASE 2: 중간 원소까지 제거해야 하는 경우
  validate(s.solution(intArrayOf(1, 5, 3, 4, 2)), intArrayOf(1, 2))
// 실제: [1, 3, 2]

// CASE 3: 동일한 원소도 제거 대상
  validate(s.solution(intArrayOf(3, 4, 3, 2)), intArrayOf(2))
// 실제: [3, 2]
}
