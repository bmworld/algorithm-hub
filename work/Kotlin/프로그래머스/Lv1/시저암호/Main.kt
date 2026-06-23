package 프로그래머스.Lv1.시저암호

import util.validate

class Solution {

  val ALPHBETS = 26
  val LC = 97..122
  val UC = 65..90
  fun solution(s: String, n: Int): String {
    return String(CharArray(s.length) {
      val char = s[it]
      val c = char.code
      val nc = c + n
      when (c) {
        in LC -> (nc - if (nc > 122) ALPHBETS else 0).toChar()
        in UC -> (nc - if (nc > 90) ALPHBETS else 0).toChar()
        else -> char
      }
    })
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.9MB)
 * 테스트 2 〉	통과 (0.02ms, 57.9MB)
 * 테스트 3 〉	통과 (0.02ms, 60.3MB)
 * 테스트 4 〉	통과 (0.02ms, 58.3MB)
 * 테스트 5 〉	통과 (0.02ms, 58.5MB)
 * 테스트 6 〉	통과 (0.02ms, 58.7MB)
 * 테스트 7 〉	통과 (0.02ms, 60MB)
 * 테스트 8 〉	통과 (0.02ms, 58.2MB)
 * 테스트 9 〉	통과 (0.02ms, 59.4MB)
 * 테스트 10 〉	통과 (0.02ms, 59.3MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(s: String, n: Int): String =
 *         s.toList().joinToString(separator = "") {
 *             when (it) {
 *                 in 'A'..'Z' -> ('A'.toInt() + (it.toInt() - 'A'.toInt() + n) % ('Z' - 'A' + 1)).toChar()
 *                 in 'a'..'z' -> ('a'.toInt() + (it.toInt() - 'a'.toInt() + n) % ('z' - 'a' + 1)).toChar()
 *                 else -> it
 *             }.toString()
 *         }
 * }
 * 테스트 1 〉	통과 (9.66ms, 61.8MB)
 * 테스트 2 〉	통과 (10.21ms, 61.8MB)
 * 테스트 3 〉	통과 (11.92ms, 60.3MB)
 * 테스트 4 〉	통과 (10.01ms, 60.8MB)
 * 테스트 5 〉	통과 (9.63ms, 60.7MB)
 * 테스트 6 〉	통과 (10.45ms, 61.1MB)
 * 테스트 7 〉	통과 (9.72ms, 60MB)
 * 테스트 8 〉	통과 (9.81ms, 61.3MB)
 * 테스트 9 〉	통과 (11.30ms, 60MB)
 * 테스트 10 〉	통과 (11.56ms, 60.3MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("ABab zZ", 1), "BCbc aA")
}
