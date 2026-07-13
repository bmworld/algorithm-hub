package 프로그래머스.Lv2.최댓값과최솟값

import util.validate

class Solution {

  val ZERO = 48
  val NINE = 57
  val NUM = ZERO..NINE
  val SPACE = ' '.code
  val MINUS = '-'.code
  fun solution(str: String): String {
    var min = Int.MAX_VALUE
    var max = Int.MIN_VALUE

    var s = 1
    var n = 0

    fun check() {
      val x = s * n
      if (x < min) min = x
      if (x > max) max = x
      s = 1
      n = 0
    }

    for (ch in str) {
      val code = ch.code
      when (code) {
        SPACE -> check()
        MINUS -> s = -1
        in NUM -> n = n * 10 + code - ZERO
      }
    }

    check()

    return "$min $max"
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (3.13ms, 59.6MB)
 * 테스트 2 〉	통과 (3.50ms, 60.6MB)
 * 테스트 3 〉	통과 (3.17ms, 60.1MB)
 * 테스트 4 〉	통과 (4.13ms, 59.2MB)
 * 테스트 5 〉	통과 (3.90ms, 60.1MB)
 * 테스트 6 〉	통과 (4.00ms, 60.2MB)
 * 테스트 7 〉	통과 (3.20ms, 58.3MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(s: String): String = s.split(" ").map { it.toInt() }.let { "${it.min()} ${it.max()}" }
 * }
 * 테스트 1 〉	통과 (11.25ms, 61.4MB)
 * 테스트 2 〉	통과 (11.77ms, 61.2MB)
 * 테스트 3 〉	통과 (10.36ms, 62.1MB)
 * 테스트 4 〉	통과 (10.62ms, 61.8MB)
 * 테스트 5 〉	통과 (10.62ms, 61.6MB)
 * 테스트 6 〉	통과 (10.36ms, 61.6MB)
 * 테스트 7 〉	통과 (10.39ms, 62.2MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("1 2 3 4"), "1 4")
  validate(s.solution("-1 -2 -3 -4"), "-4 -1")
  validate(s.solution("-1 -1"), "-1 -1")
  validate(s.solution("-1 -111 -1 111"), "-111 111")
  validate(s.solution("-1 -111 -1 111"), "-111 111")

}
