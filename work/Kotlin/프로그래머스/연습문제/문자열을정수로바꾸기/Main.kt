package 프로그래머스.연습문제.문자열을정수로바꾸기

import util.validate

class Solution {

  val ZERO = 48
  val NINE = 57
  val NUM = ZERO..NINE
  fun solution(str: String): Int {
    var s = 1
    var x = 0

    var fr = 0
    if (str[fr].code !in NUM) {
      fr++
      s = -1
    }

    for (i in fr until str.length) x = x * 10 + str[i].code - ZERO
    return s * x
  }
}

/**
 * ```
 * [ME]
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("1924"), 1924)
  validate(s.solution("-1924"), -1924)
}
