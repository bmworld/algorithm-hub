package 프로그래머스.입문.Day24.k의개수

import util.validate

class Solution {

  fun solution(i: Int, j: Int, k: Int): Int {
    var ans = 0
    for (n in i..j) {
      var x = n
      while (x > 0) {
        if (x % 10 == k) ans++
        x /= 10
      }
    }
    return ans
  }
}

/**
 * ```
 * ME:
 * 테스트 1 〉	통과 (2.74ms, 63.2MB)
 * 테스트 2 〉	통과 (0.01ms, 63.3MB)
 * 테스트 3 〉	통과 (0.01ms, 63.1MB)
 * 테스트 4 〉	통과 (0.62ms, 61.8MB)
 * 테스트 5 〉	통과 (0.34ms, 62.3MB)
 * 테스트 6 〉	통과 (0.01ms, 62.9MB)
 * 테스트 7 〉	통과 (0.05ms, 65.3MB)
 * 테스트 8 〉	통과 (1.40ms, 63.7MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * class Solution {
 *     fun solution(i: Int, j: Int, k: Int): Int = (i..j).joinToString("").count { it.digitToInt() == k }
 * }
 *
 * 테스트 1 〉	통과 (33.68ms, 74.1MB)
 * 테스트 2 〉	통과 (10.46ms, 65MB)
 * 테스트 3 〉	통과 (11.88ms, 64.8MB)
 * 테스트 4 〉	통과 (16.23ms, 66.5MB)
 * 테스트 5 〉	통과 (12.25ms, 65.8MB)
 * 테스트 6 〉	통과 (9.13ms, 64.8MB)
 * 테스트 7 〉	통과 (11.67ms, 65.5MB)
 * 테스트 8 〉	통과 (22.03ms, 68.7MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(1, 13, 1), 6)
  validate(s.solution(10, 50, 5), 5)
  validate(s.solution(3, 10, 2), 0)
}
