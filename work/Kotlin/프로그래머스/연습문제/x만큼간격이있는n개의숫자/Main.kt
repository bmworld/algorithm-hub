package 프로그래머스.연습문제.x만큼간격이있는n개의숫자

import util.validate

class Solution {

  fun solution(x: Int, n: Int): LongArray = LongArray(n) { x * (it + 1L) }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 59.8MB)
 * 테스트 2 〉	통과 (0.01ms, 58.7MB)
 * 테스트 3 〉	통과 (0.02ms, 59.6MB)
 * 테스트 4 〉	통과 (0.02ms, 58.9MB)
 * 테스트 5 〉	통과 (0.02ms, 58.7MB)
 * 테스트 6 〉	통과 (0.01ms, 59MB)
 * 테스트 7 〉	통과 (0.03ms, 59.6MB)
 * 테스트 8 〉	통과 (0.04ms, 57.9MB)
 * 테스트 9 〉	통과 (0.03ms, 58.2MB)
 * 테스트 10 〉	통과 (0.01ms, 58.1MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(2, 5), longArrayOf(2, 4, 6, 8, 10))
  validate(s.solution(-4, 2), longArrayOf(-4, -8))

  val init = 10_000_000
  val times = 1000
  validate(s.solution(init, times), LongArray(times) { init * (it + 1L) })
}
