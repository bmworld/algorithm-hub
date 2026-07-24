package 프로그래머스.Lv2.숫자변환하기

import util.validate

class Solution {
  companion object {

    const val INF = Int.MAX_VALUE
  }

  fun solution(x: Int, y: Int, n: Int): Int {
    if (x == y) return 0

    val dp = IntArray(y + 1) { INF }
    dp[x] = 0

    fun check(cur: Int, nxt: Int, end: Int) {
      if (nxt <= end) dp[nxt] = minOf(dp[nxt], dp[cur] + 1)
    }

    for (num in x..y) {
      check(num, num + n, y)
      check(num, num * 2, y)
      check(num, num * 3, y)
    }
    return if (dp[y] == INF) -1 else dp[y]
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	실패 (5.89ms, 60.4MB)
 * 테스트 2 〉	통과 (4.33ms, 63.7MB)
 * 테스트 3 〉	통과 (5.14ms, 64.4MB)
 * 테스트 4 〉	통과 (0.34ms, 60.1MB)
 * 테스트 5 〉	통과 (2.62ms, 61.7MB)
 * 테스트 6 〉	통과 (0.01ms, 61.2MB)
 * 테스트 7 〉	통과 (2.61ms, 60.7MB)
 * 테스트 8 〉	실패 (8.30ms, 65MB)
 * 테스트 9 〉	통과 (11.28ms, 64.6MB)
 * 테스트 10 〉	통과 (12.67ms, 63.9MB)
 * 테스트 11 〉	실패 (10.06ms, 65.1MB)
 * 테스트 12 〉	실패 (6.15ms, 63MB)
 * 테스트 13 〉	통과 (3.98ms, 61.8MB)
 * 테스트 14 〉	실패 (4.14ms, 61.2MB)
 * 테스트 15 〉	실패 (8.16ms, 63.7MB)
 * 테스트 16 〉	실패 (11.09ms, 64.9MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(10, 40, 5), 2)
  validate(s.solution(10, 40, 30), 1)
  validate(s.solution(2, 5, 4), -1)
  validate(s.solution(2, 2, 100), 0)
  validate(s.solution(1, 1_000_000, 1), 19)
}
