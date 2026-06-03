package 프로그래머스.알고리즘고득점Kit.Greedy

import util.validate

class Solution {

  fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
    var ans = n - lost.size

    val lstr = BooleanArray(n)
    for (i in lost) lstr[i - 1] = true
    val rsvr = BooleanArray(n)
    for (i in reserve) rsvr[i - 1] = true

    fun check(i: Int) {
      rsvr[i] = false
      ans++
    }

    repeat(n) { i ->
      if (!lstr[i]) return@repeat

      if (rsvr[i]) check(i)
      else if (i > 0 && rsvr[i - 1]) check(i - 1)
      else if (i + 1 < n && rsvr[i + 1] && !lstr[i + 1]) check(i + 1)
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.17ms, 59.8MB)
 * 테스트 2 〉	통과 (0.26ms, 59.9MB)
 * 테스트 3 〉	통과 (0.16ms, 59.6MB)
 * 테스트 4 〉	통과 (0.22ms, 58.6MB)
 * 테스트 5 〉	통과 (0.16ms, 59.1MB)
 * 테스트 6 〉	통과 (0.26ms, 59.6MB)
 * 테스트 7 〉	통과 (0.19ms, 59.3MB)
 * 테스트 8 〉	통과 (0.19ms, 59.2MB)
 * 테스트 9 〉	통과 (0.22ms, 59.5MB)
 * 테스트 10 〉	통과 (0.22ms, 59.6MB)
 * 테스트 11 〉	통과 (0.16ms, 58.4MB)
 * 테스트 12 〉	통과 (0.19ms, 59.4MB)
 * 테스트 13 〉	통과 (0.17ms, 59.1MB)
 * 테스트 14 〉	통과 (0.16ms, 59.1MB)
 * 테스트 15 〉	통과 (0.16ms, 59.5MB)
 * 테스트 16 〉	통과 (0.19ms, 58.7MB)
 * 테스트 17 〉	통과 (0.16ms, 59MB)
 * 테스트 18 〉	통과 (0.19ms, 59.3MB)
 * 테스트 19 〉	통과 (0.16ms, 59.4MB)
 * 테스트 20 〉	통과 (0.16ms, 58MB)
 * 테스트 21 〉	통과 (0.17ms, 59.5MB)
 * 테스트 22 〉	통과 (0.15ms, 59.4MB)
 * 테스트 23 〉	통과 (0.23ms, 59.3MB)
 * 테스트 24 〉	통과 (0.50ms, 59.3MB)
 * 테스트 25 〉	통과 (0.22ms, 59.8MB)
 * 테스트 26 〉	통과 (0.17ms, 59.4MB)
 * 테스트 27 〉	통과 (0.18ms, 59.6MB)
 * 테스트 28 〉	통과 (0.16ms, 58.3MB)
 * 테스트 29 〉	통과 (0.22ms, 58.7MB)
 * 테스트 30 〉	통과 (0.19ms, 58.9MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *         fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
 *
 *             var answer = n
 *             var lostSet = lost.toSet() - reserve.toSet()
 *             var reserveSet = (reserve.toSet() - lost.toSet()) as MutableSet
 *
 *             for (i in lostSet) {
 *                 when {
 *                     i + 1 in reserveSet -> reserveSet.remove(i + 1)
 *                     i - 1 in reserveSet -> reserveSet.remove(i - 1)
 *                     else -> answer--
 *                 }
 *             }
 *             return answer
 *         }
 * }
 * 테스트 1 〉	통과 (13.90ms, 63.3MB)
 * 테스트 2 〉	통과 (13.97ms, 64.1MB)
 * 테스트 3 〉	통과 (14.79ms, 63.8MB)
 * 테스트 4 〉	통과 (14.04ms, 64MB)
 * 테스트 5 〉	통과 (15.96ms, 63.1MB)
 * 테스트 6 〉	통과 (14.02ms, 63.6MB)
 * 테스트 7 〉	통과 (13.98ms, 63.5MB)
 * 테스트 8 〉	통과 (13.93ms, 62.9MB)
 * 테스트 9 〉	통과 (14.79ms, 63.5MB)
 * 테스트 10 〉	통과 (14.34ms, 63.7MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(5, intArrayOf(2, 4), intArrayOf(1, 3, 5)), 5)
  validate(s.solution(5, intArrayOf(2, 4), intArrayOf(3)), 4)
  validate(s.solution(3, intArrayOf(3), intArrayOf(1)), 2)
  validate(s.solution(3, intArrayOf(2), intArrayOf(3)), 3)
  validate(s.solution(10, intArrayOf(2, 5), intArrayOf(3, 4)), 10)
  validate(s.solution(10, intArrayOf(2, 5), intArrayOf(3, 7)), 9)
  validate(s.solution(3, intArrayOf(1), intArrayOf(1)), 3)
  validate(s.solution(3, intArrayOf(1, 2), intArrayOf(2)), 2)
  validate(s.solution(3, intArrayOf(1, 2), intArrayOf(1)), 2)
  validate(s.solution(3, intArrayOf(1, 2), intArrayOf(1, 2)), 3)
  validate(s.solution(3, intArrayOf(1, 2, 3), intArrayOf(2)), 1)
  validate(s.solution(5, intArrayOf(1, 2, 3), intArrayOf(5)), 2)
  validate(s.solution(3, intArrayOf(1, 2), intArrayOf(2, 3)), 2)
}
