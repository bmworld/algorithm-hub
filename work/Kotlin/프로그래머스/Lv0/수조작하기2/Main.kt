package 프로그래머스.Lv0.수조작하기2

import util.validate

class Solution {

  fun solution(numLog: IntArray): String =
    String(CharArray(numLog.size - 1) { i ->
      when (numLog[i + 1] - numLog[i]) {
        1 -> 'w'
        -1 -> 's'
        10 -> 'd'
        else -> 'a'
      }
    })
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (3.96ms, 65.6MB)
 * 테스트 2 〉	통과 (3.52ms, 64.7MB)
 * 테스트 3 〉	통과 (0.79ms, 60.6MB)
 * 테스트 4 〉	통과 (0.65ms, 60.6MB)
 * 테스트 5 〉	통과 (1.78ms, 62.2MB)
 * 테스트 6 〉	통과 (2.31ms, 63.2MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(numLog: IntArray): String {
 *         return (1..numLog.lastIndex).map {
 *             when (numLog[it] - numLog[it-1]) {
 *                 1 -> 'w'
 *                 -1 -> 's'
 *                 10 -> 'd'
 *                 else -> 'a'
 *             }
 *         }.joinToString("")
 *     }
 * }
 * 테스트 1 〉	통과 (28.05ms, 72.6MB)
 * 테스트 2 〉	통과 (28.53ms, 71MB)
 * 테스트 3 〉	통과 (22.09ms, 66.4MB)
 * 테스트 4 〉	통과 (20.41ms, 65.9MB)
 * 테스트 5 〉	통과 (23.26ms, 68.3MB)
 * 테스트 6 〉	통과 (24.44ms, 68.3MB)
 *
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(0, 1, 0, 10, 0, 1, 0, 10, 0, -1, -2, -1)), "wsdawsdassw")
  validate(s.solution(intArrayOf(0, 10)), "d")
}
