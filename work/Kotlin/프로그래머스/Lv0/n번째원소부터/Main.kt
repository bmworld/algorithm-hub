package 프로그래머스.Lv0.n번째원소부터

import util.validate

class Solution {

  fun solution(a: IntArray, n: Int): IntArray =
    IntArray(a.size - n + 1) { a[n - 1 + it] }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 59.7MB)
 * 테스트 2 〉	통과 (0.02ms, 59.7MB)
 * 테스트 3 〉	통과 (0.01ms, 59.7MB)
 * 테스트 4 〉	통과 (0.01ms, 60.7MB)
 * 테스트 5 〉	통과 (0.01ms, 61.2MB)
 * 테스트 6 〉	통과 (0.01ms, 59.9MB)
 * 테스트 7 〉	통과 (0.01ms, 60.4MB)
 * 테스트 8 〉	통과 (0.01ms, 60MB)
 * 테스트 9 〉	통과 (0.01ms, 60.8MB)
 * 테스트 10 〉	통과 (0.01ms, 60.8MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(numList: IntArray, n: Int) = numList.copyOfRange(n - 1, numList.size)
 * }
 * 테스트 1 〉	통과 (6.21ms, 63.4MB)
 * 테스트 2 〉	통과 (6.40ms, 63.3MB)
 * 테스트 3 〉	통과 (6.20ms, 64.5MB)
 * 테스트 4 〉	통과 (6.17ms, 64.2MB)
 * 테스트 5 〉	통과 (6.25ms, 62.7MB)
 * 테스트 6 〉	통과 (6.14ms, 63.8MB)
 * 테스트 7 〉	통과 (6.02ms, 64.2MB)
 * 테스트 8 〉	통과 (6.12ms, 63.9MB)
 * 테스트 9 〉	통과 (6.15ms, 63MB)
 * 테스트 10 〉	통과 (6.19ms, 64.5MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(2, 1, 6), 3), intArrayOf(6))
  validate(s.solution(intArrayOf(5, 2, 1, 7, 5), 2), intArrayOf(2, 1, 7, 5))
}
