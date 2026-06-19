package 프로그래머스.Lv1.내적

import util.validate

class Solution {

  fun solution(a: IntArray, b: IntArray): Int {
    var ans = 0
    repeat(a.size) {
      ans += a[it] * b[it]
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.04ms, 58.7MB)
 * 테스트 2 〉	통과 (0.01ms, 59.1MB)
 * 테스트 3 〉	통과 (0.01ms, 59.8MB)
 * 테스트 4 〉	통과 (0.01ms, 58.9MB)
 * 테스트 5 〉	통과 (0.01ms, 58.5MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(a: IntArray, b: IntArray): Int {
 *         return a.zip(b).map { it.first * it.second }.sum()
 *     }
 * }
 * 테스트 1 〉	통과 (14.12ms, 64.5MB)
 * 테스트 2 〉	통과 (12.55ms, 62.7MB)
 * 테스트 3 〉	통과 (12.70ms, 63.3MB)
 * 테스트 4 〉	통과 (12.61ms, 63.9MB)
 * 테스트 5 〉	통과 (13.13ms, 62MB)
 * 테스트 6 〉	통과 (15.20ms, 63.8MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(1, 2, 3, 4), intArrayOf(-3, -1, 0, 2)), 3)
  validate(s.solution(intArrayOf(-1, 0, 1), intArrayOf(1, 0, -1)), -2)
}
