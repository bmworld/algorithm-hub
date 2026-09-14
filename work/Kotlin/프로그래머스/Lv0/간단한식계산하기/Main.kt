package 프로그래머스.Lv0.간단한식계산하기

import util.validate

class Solution {
  companion object {

    const val SPACE = ' '.code
    const val PLS = '+'.code
    const val MIN = '-'.code
    const val MUL = '*'.code
    const val ZERO = 48
    const val NINE = 57
    val NUM = ZERO..NINE
  }

  fun solution(binomial: String): Int {
    var a = 0
    var b = 0
    var i = 0
    var op = MUL
    for (x in binomial) {
      when (val code = x.code) {
        in NUM ->
          if (i == 0) a = a * 10 + code - ZERO
          else b = b * 10 + code - ZERO
        SPACE -> continue
        else -> {
          op = code
          i++
        }
      }
    }

    return when (op) {
      PLS -> a + b
      MIN -> a - b
      else -> a * b
    }
  }
}

/**
 * ```
 * [ME]
 * * 테스트 1 〉	통과 (0.01ms, 57.6MB)
 *  * 테스트 2 〉	통과 (0.02ms, 59.5MB)
 *  * 테스트 3 〉	통과 (0.02ms, 57.8MB)
 *  * 테스트 4 〉	통과 (0.02ms, 58.3MB)
 *  * 테스트 5 〉	통과 (0.02ms, 60.1MB)
 *  * 테스트 6 〉	통과 (0.03ms, 59.8MB)
 *  * 테스트 7 〉	통과 (0.02ms, 59.2MB)
 *  * 테스트 8 〉	통과 (0.02ms, 59.7MB)
 *  * 테스트 9 〉	통과 (0.02ms, 59.4MB)
 *  * 테스트 10 〉	통과 (0.02ms, 59.6MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(binomial: String) = binomial.split(" ".toRegex()).let { (a, o, b) ->
 *         when (o) {
 *             "+" -> a.toInt() + b.toInt()
 *             "-" -> a.toInt() - b.toInt()
 *             "*" -> a.toInt() * b.toInt()
 *             else -> 0
 *         }
 *     }
 * }
 * 테스트 1 〉	통과 (6.10ms, 60.4MB)
 * 테스트 2 〉	통과 (5.60ms, 60.2MB)
 * 테스트 3 〉	통과 (6.29ms, 60.6MB)
 * 테스트 4 〉	통과 (5.34ms, 60.5MB)
 * 테스트 5 〉	통과 (5.47ms, 61MB)
 * 테스트 6 〉	통과 (6.15ms, 61.4MB)
 * 테스트 7 〉	통과 (5.57ms, 61.7MB)
 * 테스트 8 〉	통과 (5.54ms, 61.4MB)
 * 테스트 9 〉	통과 (5.53ms, 59.9MB)
 * 테스트 10 〉	통과 (6.18ms, 60MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("43 + 12"), 55)
  validate(s.solution("0 - 7777"), -7777)
  validate(s.solution("40000 * 40000"), 1600000000)
  validate(s.solution("40000 * 1"), 40000)
}
