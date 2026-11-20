package 프로그래머스.Lv0.전국대회선발고사

import util.validate

class Solution {
  companion object {

    const val SEP = 100
  }

  fun solution(rank: IntArray, attendance: BooleanArray): Int {
    val N = rank.size
    var a = (N + 1) * SEP
    var b = (N + 1) * SEP
    var c = (N + 1) * SEP
    for (i in 0 until N) if (attendance[i]) {
      val r = rank[i] * SEP
      when {
        r < a -> {
          c = b
          b = a
          a = r + i
        }
        r < b -> {
          c = b
          b = r + i
        }
        r < c -> {
          c = r + i
        }
      }
    }

    return 10000 * (a % SEP) + 100 * (b % SEP) + c % SEP
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.1MB)
 * 테스트 2 〉	통과 (0.01ms, 59.1MB)
 * 테스트 3 〉	통과 (0.02ms, 59.6MB)
 * 테스트 4 〉	통과 (0.02ms, 58.7MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(rank: IntArray, attendance: BooleanArray): Int {
 *         val result = rank.mapIndexed { i, v -> i to v }
 *             .filter { attendance[it.first] }
 *             .sortedBy { it.second }
 *         return result[0].first * 10000 + result[1].first * 100 + result[2].first
 *     }
 * }
 * 테스트 1 〉	통과 (11.90ms, 65.8MB)
 * 테스트 2 〉	통과 (11.64ms, 64.3MB)
 * 테스트 3 〉	통과 (11.21ms, 63.7MB)
 * 테스트 4 〉	통과 (11.70ms, 64.4MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(rank: IntArray, attendance: BooleanArray) = rank.withIndex()
 *         .zip(attendance.toList())
 *         .filter { it.second }
 *         .sortedBy { it.first.value }
 *         .map { it.first.index }
 *         .take(3)
 *         .let { (a, b, c) -> 10_000 * a + 100 * b + c }
 * }
 * 테스트 1 〉	통과 (15.75ms, 63.5MB)
 * 테스트 2 〉	통과 (18.47ms, 63.6MB)
 * 테스트 3 〉	통과 (17.26ms, 64.4MB)
 * 테스트 4 〉	통과 (17.50ms, 63.3MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(3, 7, 2, 5, 4, 6, 1),
    booleanArrayOf(false, true, true, true, true, false, false)), 20403)
  validate(s.solution(intArrayOf(1, 2, 3), booleanArrayOf(true, true, true)), 102)
  validate(s.solution(intArrayOf(6, 1, 5, 2, 3, 4),
    booleanArrayOf(true, false, true, false, false, true)), 50200)
}
