package 프로그래머스.Lv1.자연수뒤집어배열로만들기

import util.validate

class Solution {

  val MAX = 11
  fun solution(n: Long): IntArray {
    val tmp = IntArray(MAX)
    var len = 0
    var x = n
    while (x > 0) {
      tmp[len++] = (x % 10).toInt()
      x /= 10
    }

    val ans = IntArray(len)
    System.arraycopy(tmp, 0, ans, 0, len)
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 58.2MB)
 * 테스트 2 〉	통과 (0.01ms, 59.6MB)
 * 테스트 3 〉	통과 (0.01ms, 58.7MB)
 * 테스트 4 〉	통과 (0.01ms, 59.4MB)
 * 테스트 5 〉	통과 (0.01ms, 59.4MB)
 * 테스트 6 〉	통과 (0.01ms, 58.3MB)
 * 테스트 7 〉	통과 (0.01ms, 58.9MB)
 * 테스트 8 〉	통과 (0.01ms, 58.5MB)
 * 테스트 9 〉	통과 (0.01ms, 59.4MB)
 * 테스트 10 〉	통과 (0.01ms, 59.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(n: Long): IntArray {
 *         return n.toString().reversed().map { it.toString().toInt() }.toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (8.30ms, 61.2MB)
 * 테스트 2 〉	통과 (8.51ms, 59.8MB)
 * 테스트 3 〉	통과 (8.21ms, 61.6MB)
 * 테스트 4 〉	통과 (8.50ms, 60.7MB)
 * 테스트 5 〉	통과 (11.19ms, 60.6MB)
 * 테스트 6 〉	통과 (8.56ms, 60.2MB)
 * 테스트 7 〉	통과 (8.35ms, 61.7MB)
 * 테스트 8 〉	통과 (8.93ms, 60.1MB)
 * 테스트 9 〉	통과 (8.58ms, 60.3MB)
 * 테스트 10 〉	통과 (8.25ms, 61.2MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(1234), intArrayOf(4, 3, 2, 1))
  validate(s.solution(10_000_000_000), intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1))
}
