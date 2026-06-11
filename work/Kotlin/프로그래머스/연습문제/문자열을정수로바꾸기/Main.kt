package 프로그래머스.연습문제.문자열을정수로바꾸기

import util.validate

class Solution {

  val MINUS = 45
  val ZERO = 48
  val NINE = 57
  val NUM = ZERO..NINE
  fun solution(str: String): Int {
    var s = 1
    var x = 0

    var fr = 0
    val first = str[fr].code
    if (first !in NUM) {
      fr++
      if (first == MINUS) s = -1
    }

    for (i in fr until str.length) x = x * 10 + str[i].code - ZERO
    return s * x
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 57.6MB)
 * 테스트 2 〉	통과 (0.01ms, 58.6MB)
 * 테스트 3 〉	통과 (0.01ms, 57.7MB)
 * 테스트 4 〉	통과 (0.01ms, 58.6MB)
 * 테스트 5 〉	통과 (0.02ms, 57.5MB)
 * 테스트 6 〉	통과 (0.02ms, 58.2MB)
 * 테스트 7 〉	통과 (0.01ms, 58.3MB)
 * 테스트 8 〉	통과 (0.02ms, 58.4MB)
 * 테스트 9 〉	통과 (0.01ms, 58MB)
 * 테스트 10 〉	통과 (0.01ms, 57.8MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(s: String): Int {
 *         return s.toInt()
 *     }
 * }
 * 테스트 1 〉	통과 (0.02ms, 57.7MB)
 * 테스트 2 〉	통과 (0.02ms, 59.1MB)
 * 테스트 3 〉	통과 (0.05ms, 57.9MB)
 * 테스트 4 〉	통과 (0.01ms, 58.1MB)
 * 테스트 5 〉	통과 (0.01ms, 57.2MB)
 * 테스트 6 〉	통과 (0.01ms, 57.7MB)
 * 테스트 7 〉	통과 (0.01ms, 59.1MB)
 * 테스트 8 〉	통과 (0.02ms, 57.3MB)
 * 테스트 9 〉	통과 (0.01ms, 57.5MB)
 * 테스트 10 〉	통과 (0.03ms, 58.1MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("1924"), 1924)
  validate(s.solution("-1924"), -1924)
  validate(s.solution("+11"), 11)
  validate(s.solution("-11"), -11)
  validate(s.solution("11"), 11)
}
