package 프로그래머스.코딩기초트레이닝.n번째원소까지

import util.validate

class Solution {

  fun solution(a: IntArray, n: Int): IntArray {
    var ans = IntArray(n)
    System.arraycopy(a, 0, ans, 0, n)
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 59.2MB)
 * 테스트 2 〉	통과 (0.01ms, 59.6MB)
 * 테스트 3 〉	통과 (0.02ms, 59.7MB)
 * 테스트 4 〉	통과 (0.01ms, 58.7MB)
 * 테스트 5 〉	통과 (0.01ms, 57.7MB)
 * 테스트 6 〉	통과 (0.01ms, 58.2MB)
 * 테스트 7 〉	통과 (0.01ms, 59.2MB)
 * 테스트 8 〉	통과 (0.01ms, 58.7MB)
 * 테스트 9 〉	통과 (0.01ms, 57.9MB)
 * 테스트 10 〉	통과 (0.01ms, 58.8MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(2, 1, 6), 1), intArrayOf(2))
  validate(s.solution(intArrayOf(5, 2, 1, 7, 5), 3), intArrayOf(5, 2, 1))
}
