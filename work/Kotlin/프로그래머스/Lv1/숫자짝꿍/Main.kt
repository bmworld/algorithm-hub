package 프로그래머스.Lv1.숫자짝꿍

import util.validate

class Solution {

  val DIGITS = 10
  val ZERO = 48
  fun solution(X: String, Y: String): String {
    val cnts = IntArray(DIGITS)
    for (x in X) cnts[x.code - ZERO]++

    val pairs = IntArray(DIGITS)
    var len = 0
    for (x in Y) {
      val d = x.code - ZERO
      if (cnts[d] > 0) {
        cnts[d]--
        pairs[d]++
        len++
      }
    }

    if (len == 0) return "-1"

    val ans = CharArray(len)
    var i = 0
    for (d in 9 downTo 0) {
      if (d == 0 && i == 0) return "0"
      repeat(pairs[d]) {
        ans[i++] = (d + ZERO).toChar()
      }
    }

    return String(ans)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 58.5MB)
 * 테스트 2 〉	통과 (0.02ms, 58.5MB)
 * 테스트 3 〉	통과 (0.05ms, 60.1MB)
 * 테스트 4 〉	통과 (0.02ms, 58.7MB)
 * 테스트 5 〉	통과 (0.02ms, 59MB)
 * 테스트 6 〉	통과 (0.07ms, 59.1MB)
 * 테스트 7 〉	통과 (0.04ms, 58.8MB)
 * 테스트 8 〉	통과 (0.07ms, 58.4MB)
 * 테스트 9 〉	통과 (0.04ms, 58.6MB)
 * 테스트 10 〉	통과 (0.06ms, 59.3MB)
 * 테스트 11 〉	통과 (26.17ms, 108MB)
 * 테스트 12 〉	통과 (26.05ms, 109MB)
 * 테스트 13 〉	통과 (30.53ms, 108MB)
 * 테스트 14 〉	통과 (34.46ms, 108MB)
 * 테스트 15 〉	통과 (26.44ms, 108MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * import kotlin.math.min
 *
 * class Solution {
 *     fun solution(X: String, Y: String): String {
 *         var answer: String = ""
 *
 *         for (ch in (9 downTo 0).toList().map { it.toString() }) {
 *             answer += ch.toString().repeat(min(X.count { it.toString() == ch }, Y.count { it.toString() == ch }))
 *         }
 *         if (answer.isEmpty()) answer = "-1"
 *         if (answer.toList().distinct() == listOf('0')) answer = "0"
 *
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (10.65ms, 62.2MB)
 * 테스트 2 〉	통과 (10.75ms, 62MB)
 * 테스트 3 〉	통과 (11.36ms, 61.3MB)
 * 테스트 4 〉	통과 (11.02ms, 61.5MB)
 * 테스트 5 〉	통과 (12.06ms, 60.8MB)
 * 테스트 6 〉	통과 (13.23ms, 62.6MB)
 * 테스트 7 〉	통과 (12.35ms, 62.5MB)
 * 테스트 8 〉	통과 (19.96ms, 61MB)
 * 테스트 9 〉	통과 (11.47ms, 61.8MB)
 * 테스트 10 〉	통과 (11.53ms, 61.6MB)
 * 테스트 11 〉	통과 (450.78ms, 428MB)
 * 테스트 12 〉	통과 (436.87ms, 430MB)
 * 테스트 13 〉	통과 (491.15ms, 429MB)
 * 테스트 14 〉	통과 (540.89ms, 427MB)
 * 테스트 15 〉	통과 (452.08ms, 430MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("100", "2345"), "-1")
  validate(s.solution("100", "203045"), "0")
  validate(s.solution("100", "123450"), "10")
  validate(s.solution("12321", "42531"), "321")
  validate(s.solution("5525", "1255"), "552")
}
