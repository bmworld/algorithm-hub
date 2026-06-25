package 프로그래머스.Lv1.콜라문제

import util.validate

class Solution {

  fun solution(a: Int, b: Int, n: Int): Int {
    var ans = 0

    var x = n
    var rmn = 0
    while (x + rmn >= a) {
      val sum = x + rmn
      ans += (sum / a * b).also { x = it }
      rmn = sum % a
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.26ms, 58.6MB)
 * 테스트 2 〉	통과 (0.16ms, 58.5MB)
 * 테스트 3 〉	통과 (0.17ms, 59.6MB)
 * 테스트 4 〉	통과 (0.26ms, 59.5MB)
 * 테스트 5 〉	통과 (0.20ms, 58.9MB)
 * 테스트 6 〉	통과 (0.18ms, 57.7MB)
 * 테스트 7 〉	통과 (0.19ms, 57.8MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(a: Int, b: Int, n: Int): Int {
 *         return (if (n > b) n - b else 0) / (a - b) * b
 *     }
 * }
 * 테스트 1 〉	통과 (0.01ms, 59.7MB)
 * 테스트 2 〉	통과 (0.01ms, 58.5MB)
 * 테스트 3 〉	통과 (0.02ms, 57.6MB)
 * 테스트 4 〉	통과 (0.01ms, 57.7MB)
 * 테스트 5 〉	통과 (0.01ms, 58.1MB)
 * 테스트 6 〉	통과 (0.01ms, 59.6MB)
 * 테스트 7 〉	통과 (0.02ms, 57.9MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(2, 1, 20), 19)
  validate(s.solution(3, 1, 20), 9)
}
