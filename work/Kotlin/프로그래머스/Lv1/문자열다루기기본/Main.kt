package 프로그래머스.Lv1.문자열다루기기본

import util.validate

class Solution {

  val NUM = 48..57
  fun solution(s: String): Boolean {
    val l = s.length
    if (l != 4 && l != 6) return false
    for (x in s) if (x.code !in NUM) return false

    return true
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 58.7MB)
 * 테스트 2 〉	통과 (0.01ms, 57.5MB)
 * 테스트 3 〉	통과 (0.01ms, 58MB)
 * 테스트 4 〉	통과 (0.02ms, 59.3MB)
 * 테스트 5 〉	통과 (0.01ms, 58.6MB)
 * 테스트 6 〉	통과 (0.01ms, 58.1MB)
 * 테스트 7 〉	통과 (0.01ms, 59.4MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(s: String) = (s.length == 4 || s.length == 6) && s.toIntOrNull() != null
 * }
 * 테스트 1 〉	통과 (4.32ms, 60.4MB)
 * 테스트 2 〉	통과 (0.01ms, 58.5MB)
 * 테스트 3 〉	통과 (4.21ms, 59.8MB)
 * 테스트 4 〉	통과 (4.37ms, 59.4MB)
 * 테스트 5 〉	통과 (0.01ms, 59.2MB)
 * 테스트 6 〉	통과 (0.01ms, 59.5MB)
 * 테스트 7 〉	통과 (0.01ms, 57.8MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("1234"), true)
  validate(s.solution("123456"), true)
  validate(s.solution("1234567"), false)
  validate(s.solution("123"), false)
  validate(s.solution("a234"), false)
  validate(s.solution("12358z"), false)
}
