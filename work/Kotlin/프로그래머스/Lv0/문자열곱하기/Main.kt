package 프로그래머스.Lv0.문자열곱하기

import util.validate

class Solution {

  fun solution(my_string: String, k: Int): String {
    val N = my_string.length
    return String(CharArray(N * 3) { my_string[it % N] })
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
  validate(
    s.solution("str", 3),
    "strstrstr",
  )
}
