package 프로그래머스.Lv1.약수의합

import util.validate

class Solution {

  fun solution(n: Int): Int {
    var ans = 0
    var x = 1
    while (x <= n / x) {
      if (n % x == 0) {
        val d = n / x
        ans += d
        if (d != x) ans += x
      }
      x++
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 59.3MB)
 * 테스트 2 〉	통과 (0.01ms, 59.9MB)
 * 테스트 3 〉	통과 (0.01ms, 58.8MB)
 * 테스트 4 〉	통과 (0.01ms, 59.2MB)
 * 테스트 5 〉	통과 (0.01ms, 59.5MB)
 * 테스트 6 〉	통과 (0.01ms, 58MB)
 * 테스트 7 〉	통과 (0.01ms, 59.5MB)
 * 테스트 8 〉	통과 (0.01ms, 58.3MB)
 * 테스트 9 〉	통과 (0.01ms, 58.8MB)
 * 테스트 10 〉	통과 (0.01ms, 59.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(n: Int): Int {
 *         var answer = 0
 *
 *         answer = (1..n).filter { n % it == 0 }.sum()
 *
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (6.42ms, 59.9MB)
 * 테스트 2 〉	통과 (4.90ms, 58.7MB)
 * 테스트 3 〉	통과 (5.29ms, 60.4MB)
 * 테스트 4 〉	통과 (5.25ms, 59.3MB)
 * 테스트 5 〉	통과 (4.96ms, 60.6MB)
 * 테스트 6 〉	통과 (5.63ms, 60MB)
 * 테스트 7 〉	통과 (4.95ms, 60.5MB)
 * 테스트 8 〉	통과 (6.50ms, 59MB)
 * 테스트 9 〉	통과 (4.96ms, 60.8MB)
 * 테스트 10 〉	통과 (5.70ms, 59.7MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(12), 28)
  validate(s.solution(5), 6)
  validate(s.solution(16), 31)

}
