package 프로그래머스.Lv1.핸드폰번호가리기

import util.validate

class Solution {

  val easterLisk = '*'
  val EXCEPT = 4
  fun solution(phone_number: String): String {
    val len = phone_number.length
    return String(CharArray(len) { if (EXCEPT + it < len) easterLisk else phone_number[it] })
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 59.2MB)
 * 테스트 2 〉	통과 (0.01ms, 59.5MB)
 * 테스트 3 〉	통과 (0.01ms, 58.9MB)
 * 테스트 4 〉	통과 (0.02ms, 58.3MB)
 * 테스트 5 〉	통과 (0.01ms, 58.5MB)
 * 테스트 6 〉	통과 (0.02ms, 59.2MB)
 * 테스트 7 〉	통과 (0.01ms, 58MB)
 * ```
 *
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(phone_number: String): String {
 *         return "${"".padStart(phone_number.length - 4, '*')}${phone_number.takeLast(4)}"
 *     }
 * }
 * 테스트 1 〉	통과 (5.98ms, 60.2MB)
 * 테스트 2 〉	통과 (5.95ms, 59.7MB)
 * 테스트 3 〉	통과 (6.03ms, 59.9MB)
 * 테스트 4 〉	통과 (6.48ms, 61.1MB)
 * 테스트 5 〉	통과 (6.70ms, 60.5MB)
 * 테스트 6 〉	통과 (6.76ms, 59.7MB)
 * 테스트 7 〉	통과 (6.35ms, 59.4MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("01033334444"), "*******4444")
  validate(s.solution("027778888"), "*****8888")
}
