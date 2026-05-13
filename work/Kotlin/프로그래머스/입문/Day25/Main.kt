package 프로그래머스.입문.Day25

import util.validate

class Solution {

  fun solution(a: IntArray): Int {
    val v1 = a[0]
    val v2 = a[1]
    val v3 = a[2]

    val d1 = v2 - v1
    val d2 = v3 - v2

    return if (d1 == d2) a[a.size - 1] + d1 else a[a.size - 1] * v2 / v1
  }
}

/**
 * ```
 * ME:
 * 테스트 1 〉	통과 (0.02ms, 62.4MB)
 * 테스트 2 〉	통과 (0.01ms, 64.8MB)
 * 테스트 3 〉	통과 (0.01ms, 63.8MB)
 * 테스트 4 〉	통과 (0.02ms, 63.1MB)
 * 테스트 5 〉	통과 (0.01ms, 63.6MB)
 * 테스트 6 〉	통과 (0.01ms, 62.3MB)
 * 테스트 7 〉	통과 (0.02ms, 63.3MB)
 * 테스트 8 〉	통과 (0.01ms, 63.3MB)
 * 테스트 9 〉	통과 (0.01ms, 63.6MB)테스트 1 〉	통과 (0.02ms, 62.4MB)
 * 테스트 2 〉	통과 (0.01ms, 64.8MB)
 * 테스트 3 〉	통과 (0.01ms, 63.8MB)
 * 테스트 4 〉	통과 (0.02ms, 63.1MB)
 * 테스트 5 〉	통과 (0.01ms, 63.6MB)
 * 테스트 6 〉	통과 (0.01ms, 62.3MB)
 * 테스트 7 〉	통과 (0.02ms, 63.3MB)
 * 테스트 8 〉	통과 (0.01ms, 63.3MB)
 * 테스트 9 〉	통과 (0.01ms, 63.6MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(1, 2, 3, 4)), 5)
  validate(s.solution(intArrayOf(1, 2, 4, 8)), 16)
}
