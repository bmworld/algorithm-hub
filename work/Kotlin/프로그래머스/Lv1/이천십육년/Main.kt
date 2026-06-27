package 프로그래머스.Lv1.이천십육년

import util.validate

class Solution {

  val days = arrayOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")
  val months = intArrayOf(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
  fun solution(a: Int, b: Int): String {
    var date = 5 + b - 1
    repeat(a - 1) {
      date += months[it]
    }
    return days[date % 7]
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 58.2MB)
 * 테스트 2 〉	통과 (0.01ms, 60.9MB)
 * 테스트 3 〉	통과 (0.01ms, 57.9MB)
 * 테스트 4 〉	통과 (0.01ms, 59.6MB)
 * 테스트 5 〉	통과 (0.01ms, 58.7MB)
 * 테스트 6 〉	통과 (0.01ms, 58.3MB)
 * 테스트 7 〉	통과 (0.01ms, 59.2MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(a: Int, b: Int): String {
 *         val week = listOf("THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED")
 *
 *         var answer = ""
 *         var days = b
 *
 *         for (i in 1 until a) {
 *             days += when (i) {
 *                 1, 3, 5, 7, 8, 10, 12 -> 31
 *                 2 -> 29
 *                 else -> 30
 *             }
 *         }
 *
 *         answer = week[days % 7]
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (9.34ms, 63.3MB)
 * 테스트 2 〉	통과 (9.17ms, 62.5MB)
 * 테스트 3 〉	통과 (9.22ms, 63.9MB)
 * 테스트 4 〉	통과 (9.18ms, 62.7MB)
 * 테스트 5 〉	통과 (9.06ms, 63.7MB)
 * 테스트 6 〉	통과 (9.06ms, 64.4MB)
 * 테스트 7 〉	통과 (9.34ms, 62.4MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(5, 24), "TUE")
  validate(s.solution(1, 1), "FRI")
  validate(s.solution(8, 1), "MON")
}
