package 프로그래머스.Lv2.JadenCase문자열만들기

import util.validate

class Solution {

  val ZERO = 48
  val NINE = 57
  val NUM = ZERO..NINE

  val SPACE = 32
  val ALPHABETS = 26
  val CASE_DIFF = 32
  private val A = 65
  private val a = 97
  val LOWERCASE = a until a + ALPHABETS
  val UPPERCASE = A until A + ALPHABETS

  fun solution(s: String): String {
    val ans = CharArray(s.length) { s[it] }

    var j = 0
    for (i in 0 until s.length) {
      val code = s[i].code
      if (j == 0 && code in LOWERCASE) ans[i] = toUppercase(code)
      else if (j > 0 && code in UPPERCASE) ans[i] = toLowercase(code)

      if (code == SPACE) j = 0 else j++
    }

    return String(ans)
  }

  fun toUppercase(code: Int): Char = (code - CASE_DIFF).toChar()
  fun toLowercase(code: Int): Char = (code + CASE_DIFF).toChar()
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 60.4MB)
 * 테스트 2 〉	통과 (0.03ms, 60MB)
 * 테스트 3 〉	통과 (0.03ms, 59.7MB)
 * 테스트 4 〉	통과 (0.03ms, 60.9MB)
 * 테스트 5 〉	통과 (0.04ms, 60.7MB)
 * 테스트 6 〉	통과 (0.03ms, 60.3MB)
 * 테스트 7 〉	통과 (0.03ms, 59.9MB)
 * 테스트 8 〉	통과 (0.02ms, 60MB)
 * 테스트 9 〉	통과 (0.02ms, 60.4MB)
 * 테스트 10 〉	통과 (0.02ms, 60.3MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(s: String): String {
 *         return s
 *             .lowercase()
 *             .split(" ")
 *             .map {
 *                 it.replaceFirstChar { c ->
 *                     if (c.isLowerCase()) c.titlecase() else c.toString()
 *                 }
 *             }
 *             .joinToString(" ")
 *     }
 * }
 * 테스트 1 〉	통과 (9.24ms, 61.1MB)
 * 테스트 2 〉	통과 (9.32ms, 62.1MB)
 * 테스트 3 〉	통과 (8.62ms, 61.6MB)
 * 테스트 4 〉	통과 (8.72ms, 61.9MB)
 * 테스트 5 〉	통과 (8.62ms, 61.2MB)
 * 테스트 6 〉	통과 (8.62ms, 61.9MB)
 * 테스트 7 〉	통과 (9.93ms, 62.1MB)
 * 테스트 8 〉	통과 (9.06ms, 61.2MB)
 * 테스트 9 〉	통과 (9.54ms, 60.9MB)
 * 테스트 10 〉	통과 (9.15ms, 60.4MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("3people unFollowed me"), "3people Unfollowed Me")
  validate(s.solution("for the last week"), "For The Last Week")
}
