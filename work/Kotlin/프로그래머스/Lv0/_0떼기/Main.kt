package 프로그래머스.Lv0._0떼기

import util.validate

class Solution {
  companion object {

    const val ZERO = '0'
  }

  fun solution(n_str: String): String {
    var removed = false
    val ans = CharArray(n_str.length)
    var len = 0
    for (x in n_str) {
      if (removed) ans[len++] = x
      else if (x != ZERO) {
        removed = true
        ans[len++] = x
      }
    }
    return String(ans.copyOf(len))
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59MB)
 * 테스트 2 〉	통과 (0.02ms, 59.7MB)
 * 테스트 3 〉	통과 (0.02ms, 61.4MB)
 * 테스트 4 〉	통과 (0.01ms, 60.1MB)
 * 테스트 5 〉	통과 (0.02ms, 60.5MB)
 * 테스트 6 〉	통과 (0.02ms, 60.4MB)
 * 테스트 7 〉	통과 (0.02ms, 60.1MB)
 * 테스트 8 〉	통과 (0.02ms, 59.3MB)
 * 테스트 9 〉	통과 (0.02ms, 59.5MB)
 * 테스트 10 〉	통과 (0.02ms, 59.9MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(nStr: String) = nStr.toInt().toString()
 * }
 * 테스트 1 〉	통과 (0.02ms, 59.9MB)
 * 테스트 2 〉	통과 (0.02ms, 60.5MB)
 * 테스트 3 〉	통과 (0.02ms, 59.5MB)
 * 테스트 4 〉	통과 (0.02ms, 60.3MB)
 * 테스트 5 〉	통과 (0.02ms, 60.1MB)
 * 테스트 6 〉	통과 (0.02ms, 61.3MB)
 * 테스트 7 〉	통과 (0.02ms, 59.8MB)
 * 테스트 8 〉	통과 (0.02ms, 60.2MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(n_str: String): String {
 *         return n_str
 *             .dropWhile { it == '0' }
 *     }
 * }
 * 테스트 1 〉	통과 (0.01ms, 60.4MB)
 * 테스트 2 〉	통과 (0.01ms, 59.7MB)
 * 테스트 3 〉	통과 (0.01ms, 60.6MB)
 * 테스트 4 〉	통과 (0.02ms, 60.3MB)
 * 테스트 5 〉	통과 (0.02ms, 59.3MB)
 * 테스트 6 〉	통과 (0.01ms, 59.7MB)
 * 테스트 7 〉	통과 (0.01ms, 60MB)
 * 테스트 8 〉	통과 (0.01ms, 60.1MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("0010"), "10")
  validate(s.solution("10010"), "10010")
}
