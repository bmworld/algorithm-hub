package 프로그래머스.Lv1.정수제곱근판별

import util.validate

class Solution {

  fun solution(n: Long): Long {
    var ans = -1L
    var l = 1L
    var r = n
    while (l <= r) {
      val m = (l + r) shr 1
      val pow = m * m
      when {
        pow > n -> r = m - 1
        pow < n -> l = m + 1
        else -> return (m + 1) * (m + 1)
      }
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 58.8MB)
 * 테스트 2 〉	실패 (0.02ms, 57.6MB)
 * 테스트 3 〉	통과 (0.02ms, 59.7MB)
 * 테스트 4 〉	실패 (0.02ms, 58.3MB)
 * 테스트 5 〉	통과 (0.05ms, 59.3MB)
 * 테스트 6 〉	통과 (0.02ms, 59.7MB)
 * 테스트 7 〉	통과 (0.01ms, 57.9MB)
 * 테스트 8 〉	통과 (0.01ms, 59MB)
 * 테스트 9 〉	통과 (0.01ms, 57.8MB)
 * 테스트 10 〉	통과 (0.02ms, 57.9MB)
 * 테스트 11 〉	실패 (0.02ms, 57.7MB)
 * 테스트 12 〉	실패 (0.02ms, 57.6MB)
 * 테스트 13 〉	통과 (0.02ms, 59.3MB)
 * 테스트 14 〉	통과 (0.01ms, 58.1MB)
 * 테스트 15 〉	통과 (0.01ms, 59.2MB)
 * 테스트 16 〉	통과 (0.01ms, 57.6MB)
 * 테스트 17 〉	통과 (0.01ms, 59.3MB)
 * 테스트 18 〉	통과 (0.01ms, 57.8MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(121), 144)
  validate(s.solution(1), 4)
  validate(s.solution(998_001), 1_000_000)
  validate(s.solution(50_000_000_000_000), -1L)
}
