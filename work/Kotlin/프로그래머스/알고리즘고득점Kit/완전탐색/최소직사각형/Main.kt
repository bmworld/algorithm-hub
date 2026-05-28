package 프로그래머스.알고리즘고득점Kit.완전탐색.최소직사각형

import util.validate

class Solution {

  fun solution(sizes: Array<IntArray>): Int {
    var maxW = 0
    var maxH = 0

    for (size in sizes) {
      var w = size[0]
      var h = size[1]
      if (h > w) {
        val t = w
        w = h
        h = t
      }

      if (w > maxW) maxW = w
      if (h > maxH) maxH = h
    }

    return maxW * maxH
  }
}

/**
 * ```
 * ME:
 * 테스트 1 〉	통과 (0.01ms, 65.1MB)
 * 테스트 2 〉	통과 (0.01ms, 63.5MB)
 * 테스트 3 〉	통과 (0.01ms, 63.7MB)
 * 테스트 4 〉	통과 (0.01ms, 62.8MB)
 * 테스트 5 〉	통과 (0.01ms, 65.4MB)
 * 테스트 6 〉	통과 (0.02ms, 61.9MB)
 * 테스트 7 〉	통과 (0.02ms, 63.1MB)
 * 테스트 8 〉	통과 (0.01ms, 65.4MB)
 * 테스트 9 〉	통과 (0.01ms, 63.7MB)
 * 테스트 10 〉	통과 (0.02ms, 63.6MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * class Solution {
 *     fun solution(sizes: Array<IntArray>): Int
 *         = sizes.map{it.maxOrNull()!!}.maxOrNull()!!*sizes.map{it.minOrNull()!!}.maxOrNull()!!
 * }
 * 테스트 1 〉	통과 (12.79ms, 67.1MB)
 * 테스트 2 〉	통과 (12.86ms, 66.4MB)
 * 테스트 3 〉	통과 (13.04ms, 66.4MB)
 * 테스트 4 〉	통과 (12.99ms, 66.8MB)
 * 테스트 5 〉	통과 (12.89ms, 67.2MB)
 * 테스트 6 〉	통과 (15.21ms, 66MB)
 * 테스트 7 〉	통과 (13.40ms, 66.8MB)
 * 테스트 8 〉	통과 (16.16ms, 66.4MB)
 * 테스트 9 〉	통과 (12.73ms, 66.9MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf(
      intArrayOf(60, 50),
      intArrayOf(30, 70),
      intArrayOf(60, 30),
      intArrayOf(80, 40),
    )
  ), 4000)

  validate(s.solution(
    arrayOf(
      intArrayOf(10, 7),
      intArrayOf(12, 3),
      intArrayOf(8, 15),
      intArrayOf(14, 7),
      intArrayOf(5, 15),
    )
  ), 120)

  validate(s.solution(
    arrayOf(
      intArrayOf(14, 4),
      intArrayOf(19, 6),
      intArrayOf(6, 16),
      intArrayOf(18, 7),
      intArrayOf(7, 11),
    )
  ), 133)


}
