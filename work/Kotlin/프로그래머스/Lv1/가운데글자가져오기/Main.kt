package 프로그래머스.Lv1.가운데글자가져오기

import util.validate

class Solution {

  fun solution(s: String): String {
    val len = s.length
    val half = len / 2
    return String(when {
      len % 2 == 0 -> CharArray(2) {
        if (it == 0) s[half - 1] else s[half]
      }
      else -> CharArray(1) { s[half] }
    })
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 58.4MB)
 * 테스트 2 〉	통과 (0.02ms, 58.2MB)
 * 테스트 3 〉	통과 (0.02ms, 58.9MB)
 * 테스트 4 〉	통과 (0.01ms, 58.8MB)
 * 테스트 5 〉	통과 (0.01ms, 58.8MB)
 * ```
 *
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *   fun solution(s: String) =
 *     with(s) { substring(length / 2 - 1 + (length % 2) .. length / 2) }
 * }
 * 테스트 1 〉	통과 (5.35ms, 60.3MB)
 * 테스트 2 〉	통과 (5.14ms, 59.8MB)
 * 테스트 3 〉	통과 (5.38ms, 60.4MB)
 * 테스트 4 〉	통과 (5.64ms, 58.6MB)
 * 테스트 5 〉	통과 (5.18ms, 60.4MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("abcde"), "c")
  validate(s.solution("abcd"), "bc")
}
