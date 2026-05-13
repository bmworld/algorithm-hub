package 프로그래머스.입문.Day25.종이자르기

import util.validate

class Solution {

  fun solution(M: Int, N: Int): Int = M * N - 1
}

/**
 * ```
 * ME:
 * 테스트 1 〉	통과 (0.01ms, 62.5MB)
 * 테스트 2 〉	통과 (0.01ms, 62.5MB)
 * 테스트 3 〉	통과 (0.01ms, 64.7MB)
 * 테스트 4 〉	통과 (0.01ms, 62.6MB)
 * 테스트 5 〉	통과 (0.01ms, 63.8MB)
 * 테스트 6 〉	통과 (0.01ms, 63MB)
 * 테스트 7 〉	통과 (0.01ms, 62.4MB)
 * 테스트 8 〉	통과 (0.01ms, 62.4MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(2, 2), 3)
  validate(s.solution(2, 5), 9)
  validate(s.solution(1, 1), 0)
}
