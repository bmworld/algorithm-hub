package 프로그래머스.Lv0.문자열뒤의n글자

import util.validate

class Solution {

  fun solution(my_string: String, n: Int): String {
    val N = my_string.length
    return String(CharArray(n) { my_string[N - n + it] })
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
  validate(s.solution("abc", 3), "abc")
  validate(s.solution("abc", 2), "bc")
  validate(s.solution("abc", 1), "c")
}
