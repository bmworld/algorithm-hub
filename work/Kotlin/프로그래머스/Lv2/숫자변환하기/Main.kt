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
      if (dp[num] == INF) continue
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
 * 테스트 1 〉	통과 (2.69ms, 62.7MB)
 * 테스트 2 〉	통과 (4.73ms, 65.3MB)
 * 테스트 3 〉	통과 (3.23ms, 63.5MB)
 * 테스트 4 〉	통과 (0.17ms, 61.2MB)
 * 테스트 5 〉	통과 (2.40ms, 60.7MB)
 * 테스트 6 〉	통과 (0.01ms, 60.4MB)
 * 테스트 7 〉	통과 (2.52ms, 60.7MB)
 * 테스트 8 〉	통과 (6.17ms, 62.5MB)
 * 테스트 9 〉	통과 (13.81ms, 64.4MB)
 * 테스트 10 〉	통과 (11.89ms, 65.1MB)
 * 테스트 11 〉	통과 (10.97ms, 65.1MB)
 * 테스트 12 〉	통과 (3.43ms, 63.3MB)
 * 테스트 13 〉	통과 (4.39ms, 63.9MB)
 * 테스트 14 〉	통과 (11.42ms, 62.8MB)
 * 테스트 15 〉	통과 (5.45ms, 63.5MB)
 * 테스트 16 〉	통과 (6.46ms, 64.9MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *
 *     var result = 0
 *
 *     fun solution(x: Int, y: Int, n: Int): Int {
 *         val MAX = 20
 *         result = MAX
 *         dfs(x, y, n)
 *         return if(result == MAX) -1 else result
 *     }
 *
 *     fun dfs(x: Int, y: Int, n: Int, cnt: Int = 0) {
 *         if(x == y) {
 *             result = result.coerceAtMost(cnt)
 *             return
 *         } else if(x > y || cnt >= result) {
 *             return
 *         }
 *
 *         dfs(x * 2, y, n, cnt + 1)
 *         dfs(x * 3, y, n, cnt + 1)
 *         dfs(x + n, y, n, cnt + 1)
 *     }
 * }
 * 테스트 1 〉	통과 (0.01ms, 60MB)
 * 테스트 2 〉	통과 (0.01ms, 57.9MB)
 * 테스트 3 〉	통과 (0.01ms, 60.1MB)
 * 테스트 4 〉	통과 (0.01ms, 60.8MB)
 * 테스트 5 〉	통과 (484.34ms, 58.7MB)
 * 테스트 6 〉	통과 (1.25ms, 60.6MB)
 * 테스트 7 〉	통과 (271.97ms, 58.6MB)
 * 테스트 8 〉	통과 (2.02ms, 59.9MB)
 * 테스트 9 〉	통과 (2318.86ms, 58.1MB)
 * 테스트 10 〉	통과 (1830.61ms, 58.6MB)
 * 테스트 11 〉	통과 (98.78ms, 60.3MB)
 * 테스트 12 〉	통과 (0.01ms, 61MB)
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
