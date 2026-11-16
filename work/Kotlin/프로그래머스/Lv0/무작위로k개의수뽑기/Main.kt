package 프로그래머스.Lv0.무작위로k개의수뽑기

import util.validate

class Solution {
  companion object {

    const val MAX = 100_000
    const val EMPTY = -1
  }

  fun solution(arr: IntArray, k: Int): IntArray {
    val ch = BooleanArray(MAX + 1)
    val ans = IntArray(k) { EMPTY }
    var i = 0
    for (x in arr) if (!ch[x]) {
      ch[x] = true
      ans[i] = x
      if (++i >= k) break
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.04ms, 59.4MB)
 * 테스트 2 〉	통과 (0.05ms, 58.6MB)
 * 테스트 3 〉	통과 (0.04ms, 59.3MB)
 * 테스트 4 〉	통과 (0.06ms, 59.9MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(arr: IntArray, k: Int): List<Int> {
 *         return (0 until k).map { if (it >= arr.toSet().size) -1 else arr.distinct()[it] }
 *     }
 * }
 * 테스트 1 〉	통과 (19.58ms, 63.8MB)
 * 테스트 2 〉	통과 (15.94ms, 65.9MB)
 * 테스트 3 〉	통과 (20.36ms, 65MB)
 * 테스트 4 〉	통과 (16.66ms, 64.9MB)
 * 테스트 5 〉	실행 중단
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(arr: IntArray, k: Int) = arr.distinct().toMutableList().apply {
 *         if (size > k) return subList(0, k)
 *         else repeat(k - size) { add(-1) }
 *     }
 * }
 * 테스트 1 〉	통과 (14.64ms, 64.1MB)
 * 테스트 2 〉	통과 (14.40ms, 63.9MB)
 * 테스트 3 〉	통과 (13.87ms, 64.9MB)
 * 테스트 4 〉	통과 (14.61ms, 64.8MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(0, 1, 1, 2, 2, 3), 3), intArrayOf(0, 1, 2))
  validate(s.solution(intArrayOf(0, 1, 1, 1, 1), 4), intArrayOf(0, 1, -1, -1))
  validate(s.solution(intArrayOf(0, 0, 2, 4, 1), 2), intArrayOf(0, 2))
  validate(s.solution(intArrayOf(0, 0, 0, 0), 2), intArrayOf(0, -1))
}
