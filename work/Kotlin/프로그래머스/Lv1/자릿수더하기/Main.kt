package 프로그래머스.Lv1.자릿수더하기

import util.validate

class Solution {

  fun solution(n: Int): Int {
    var ans = 0
    var x = n
    while (x > 0) {
      ans += x % 10
      x /= 10
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 59.7MB)
 * 테스트 2 〉	통과 (0.01ms, 59MB)
 * 테스트 3 〉	통과 (0.01ms, 57.8MB)
 * 테스트 4 〉	통과 (0.01ms, 58.9MB)
 * 테스트 5 〉	통과 (0.01ms, 58.6MB)
 * 테스트 6 〉	통과 (0.01ms, 59.8MB)
 * 테스트 7 〉	통과 (0.01ms, 59.4MB)
 * 테스트 8 〉	통과 (0.01ms, 58.4MB)
 * 테스트 9 〉	통과 (0.01ms, 58.9MB)
 * 테스트 10 〉	통과 (0.01ms, 58.7MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(123), 6)
  validate(s.solution(456), 15)
}
